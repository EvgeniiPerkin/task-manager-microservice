package ru.lending.microservice.task.manager.controller;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Theme;
import ru.lending.microservice.task.manager.entity.dto.ThemeDto;
import ru.lending.microservice.task.manager.exception.Error;
import ru.lending.microservice.task.manager.exception.NotFoundException;
import ru.lending.microservice.task.manager.servce.ThemeService;

@Tag(name = "Темы задач.", description = "Запросы к конроллеру тем задач.")
@RequestMapping(value = "/api/v1/themes", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ThemeController {
	@Autowired
	private ThemeService themeService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ThemeController.class);

    @Operation(
		summary = "Поиск темы по идентификатору отдела.",
    	responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Возвращает список тем для конкретного отдела.",
				content = @Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					array = @ArraySchema(
						schema = @Schema(
							name = "LoanParameter",
							implementation = Theme.class
						)
					)
				)
			)
    	}
	)
	@GetMapping("/{id}")
    Mono<ResponseEntity<Flux<Theme>>> findByDepartmentId(@Parameter(description = "Идентификатор отдела.") @PathVariable Long id) {
    	LOGGER.info("Поиск темы по идентификатору отдела: {}.", id);
		return Mono.just(ok(themeService.findByDepartmentId(id)));
	}

    @Operation(
		summary = "Создает новую тему задач.",
    	responses = {
			@ApiResponse(
				responseCode = "201",
				description = "Возвращает созданную тему.",
				content = @Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					array = @ArraySchema(
						schema = @Schema(
							name = "LoanParameter",
							implementation = Theme.class
						)
					)
				)
			)
    	}
	)
	@PostMapping
    Mono<ResponseEntity<Theme>> create(@Parameter(description = "Данные для создания темы.") @Valid @RequestBody Mono<ThemeDto> theme) {
        return themeService.create(theme)
    		.map(savedTheme -> ResponseEntity.status(HttpStatus.CREATED).body(savedTheme))
    		.doOnNext(t -> {
    			LOGGER.info("Создание новой темы '{}', для отдела (идентификатор): '{}'.",
					t.getBody().getDescription(),
					t.getBody().getDepartmentId());
		});
	}
	
    @PutMapping("/{id}")
    @Operation(
		summary = "Обновление/изменение темы.",
    	responses = {
			@ApiResponse(
				responseCode = "204",
				description = "Возвращает измененную тему.",
				content = @Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					array = @ArraySchema(
						schema = @Schema(
							name = "LoanParameter",
							implementation = Theme.class
						)
					)
				)
			),
            @ApiResponse(
        		responseCode = "404",
              	description = "Не удается изменить данные, т.к. запись отсутствует.",
              	content = @Content(
          			mediaType = MediaType.APPLICATION_JSON_VALUE,
					array = @ArraySchema(
						schema = @Schema(
							name = "Error",
							implementation = Error.class
						)
					)
    		  	)
            )
    	}
	)
    Mono<ResponseEntity<Theme>> update(
		@Parameter(description = "Данные темы для ее изменения.")
		@Valid
		@RequestBody Mono<Theme> theme) {
    	Mono<Theme> mono = theme.cache();
    	return validate(mono)
			.flatMap(t -> themeService.update(mono)
	        	.doOnNext(l -> {
	        		LOGGER.info("Изменение темы, идентификатор: {}.", l.getId()); 
	        	})
				.map(savedTheme -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(savedTheme)));
    }
    
    private Mono<Theme> validate(Mono<Theme> theme) {
        return theme
        	.doOnNext(l -> {
        		LOGGER.info("Поиск темы для ее изменения, идентификатор: {}.", l.getId()); 
        	})
        	.flatMap(t -> themeService.findById(t.getId()))
    		.switchIfEmpty(Mono.error(new NotFoundException("Измение темы задачи. Тема не найдена.")));
    }

    @Operation(
		summary = "Удаление темы по ее идентификатору.",
    	responses = {
			@ApiResponse(
				responseCode = "202",
				description = "Тема удалена."
			),
            @ApiResponse(
        		responseCode = "404",
              	description = "Не удается удалить данные, т.к. запись отсутствует.",
              	content = @Content(
          			mediaType = MediaType.APPLICATION_JSON_VALUE,
					array = @ArraySchema(
						schema = @Schema(
							name = "Error",
							implementation = Error.class
						)
					)
    		  	)
            )
		}
	)
	@DeleteMapping("/{id}")
    Mono<ResponseEntity<Void>> deleteById(
		@Parameter(description = "Идентификатор темы.")
		@RequestParam(required = true) Long id) {
    	LOGGER.info("Удаление темы по ее идентификатору: {}.", id);
		return themeService.findById(id)
			.flatMap(t -> themeService.deleteById(t.getId())
				.then(Mono.just(status(HttpStatus.ACCEPTED).<Void>build())))
            .switchIfEmpty(Mono.error(new NotFoundException("Удаление темы задачи. Тема не найдена.")));
	}
}
