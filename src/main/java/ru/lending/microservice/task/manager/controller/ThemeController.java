package ru.lending.microservice.task.manager.controller;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Theme;
import ru.lending.microservice.task.manager.entity.dto.ThemeDto;
import ru.lending.microservice.task.manager.mapper.ThemeMapper;
import ru.lending.microservice.task.manager.response.ThemeResponse;
import ru.lending.microservice.task.manager.servce.ThemeService;

@Tag(name = "Темы задач.", description = "Запросы к конроллеру тем задач.")
@RequestMapping("/api/v1/themes")
@RestController
public class ThemeController {
	@Autowired
	private ThemeService themeService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ThemeController.class);

    @Operation(summary = "Поиск темы по идентификатору отдела.")
	@GetMapping("/{id}")
    Mono<ResponseEntity<Flux<Theme>>> findByDepartmentId(@Parameter(description = "Идентификатор отдела.") @PathVariable Long id) {
    	LOGGER.info("Поиск темы по идентификатору отдела: {}", id);
		return Mono.just(ok(themeService.findByDepartmentId(id)));
	}

    @Operation(summary = "Создание новой темы.")
	@PostMapping
    Mono<ResponseEntity<ThemeResponse>> create(@Parameter(description = "Данные для создания темы.") @Valid @RequestBody ThemeDto theme) {
    	LOGGER.info("Создание новой темы {}, для отдела (идентификатор): {}", theme.description(),theme.departmentId());
    	return themeService.create(theme)
			.map(savedTheme -> ResponseEntity.status(HttpStatus.CREATED).body(savedTheme))
			.onErrorResume(WebExchangeBindException.class,
					ex -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body(ThemeMapper.fromWebExchangeBindException(ex))));
	}
	
    @PutMapping("/{id}")
    @Operation(summary = "Обновление/изменение темы.")
    Mono<ResponseEntity<Theme>> update(@Parameter(description = "Данные темы для ее изменения.") @RequestBody Theme theme) {  
    	LOGGER.info("Изменение темы, идентификатор: {}", theme.getId());    
    	return themeService.update(theme)
            .map(savedTheme -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(savedTheme));
    }

    @Operation(summary = "Удаление темы по ее идентификатору.")
	@DeleteMapping("/{id}")
    Mono<ResponseEntity<Void>> deleteById(@Parameter(description = "Идентификатор темы.") @PathVariable Long id) {
    	LOGGER.info("Удаление темы по ее идентификатору: {}", id);
		return themeService.findById(id)
			.flatMap(t -> themeService.deleteById(t.getId())
				.then(Mono.just(status(HttpStatus.ACCEPTED).<Void>build())))
            .switchIfEmpty(Mono.just(notFound().build()));
	}
}
