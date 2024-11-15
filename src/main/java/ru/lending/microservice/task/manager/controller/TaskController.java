package ru.lending.microservice.task.manager.controller;

import org.springdoc.core.annotations.RouterOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
// дял анатации возвращамемых данных
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import org.springframework.http.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Task;
import ru.lending.microservice.task.manager.entity.ViewTask;
import ru.lending.microservice.task.manager.entity.dto.TaskDto;
import ru.lending.microservice.task.manager.entity.dto.ViewTaskDto;
import ru.lending.microservice.task.manager.servce.TaskService;

@Tag(name = "Задачи", description = "Запросы к конроллеру задач.")
@RequestMapping("/api/v1/tasks")
@RestController
public class TaskController {
	@Autowired
	private TaskService taskService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Operation(summary = "Постраничный поиск задач с фильтрацией.")
	@GetMapping
	Mono<Page<ViewTask>> findByFilterAndPagination(
			@Parameter(description = "Номер страницы") @RequestParam int page, 
			@Parameter(description = "Кол-во задач на странице") @RequestParam int size,
			@Parameter(description = "Данные для фильтрации") ViewTaskDto dto) {
    	LOGGER.info("Employee add: {}", page);
		return taskService.findByFilterAndPagination(PageRequest.of(page, size), dto);
	}
	
	@GetMapping("/{id}")
    @Operation(summary = "Поиск задачи по идентификатору.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Задача найдена."),
			@ApiResponse(responseCode = "404", description = "Задача не найдена.") })
	Mono<ResponseEntity<Task>> findById(@PathVariable Long id) {
		return taskService.findById(id)
				.map(t -> ResponseEntity.ok(t));
	}
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создание задачи.")
    Mono<ResponseEntity<Task>> create(@RequestBody TaskDto tsk) {
		return taskService.create(tsk)
                .map(savedTsk -> ResponseEntity.status(HttpStatus.CREATED).body(savedTsk));
	}

	@DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удаление задачи по идентификатору.")
    Mono<Void> deleteById(@PathVariable Long id) {
		return taskService.deleteById(id);
	}
}
