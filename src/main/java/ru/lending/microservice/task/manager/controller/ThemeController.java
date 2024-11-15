package ru.lending.microservice.task.manager.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Theme;
import ru.lending.microservice.task.manager.entity.dto.ThemeDto;
import ru.lending.microservice.task.manager.servce.ThemeService;

@RequestMapping("/api/v1/themes")
@RestController
public class ThemeController {
	@Autowired
	private ThemeService themeService;

	@GetMapping("/{id}")
    @Operation(summary = "Поиск темы по идентификатору отдела.")
	Flux<Theme> findByDepartmentId(@PathVariable Long id) {
		return themeService.findByDepartmentId(id);
	}
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создание новой темы.")
    Mono<ResponseEntity<Theme>> create(@RequestBody ThemeDto theme) {
		return themeService.create(theme)
                .map(savedTheme -> ResponseEntity.status(HttpStatus.CREATED).body(savedTheme));
	}

	@DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удаление темы по ее идентификатору.")
    Mono<Void> deleteById(@PathVariable Long id) {
		return themeService.deleteById(id);
	}
	
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Обновление/изменение темы.")
    Mono<Theme> update(@RequestBody Theme theme) {      
      return themeService.update(theme);
    }
}
