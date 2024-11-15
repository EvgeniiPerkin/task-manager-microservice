package ru.lending.microservice.task.manager.controller;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.status;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Task;
import ru.lending.microservice.task.manager.entity.ViewTask;
import ru.lending.microservice.task.manager.entity.dto.TaskDto;
import ru.lending.microservice.task.manager.entity.dto.ViewTaskDto;
import ru.lending.microservice.task.manager.servce.TaskService;

@Tag(name = "Задачи.", description = "Запросы к конроллеру задач.")
@RequestMapping("/api/v1/tasks")
@RestController
public class TaskController {
	@Autowired
	private TaskService taskService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Operation(summary = "Постраничный поиск задач с фильтрацией.")
	@GetMapping
	Mono<ResponseEntity<Page<ViewTask>>> findByFilterAndPagination(
			@Parameter(description = "Номер страницы.") @RequestParam int page, 
			@Parameter(description = "Кол-во задач на странице.") @RequestParam int size,
			@Parameter(description = "Данные для фильтрации.") ViewTaskDto flt) {
    	LOGGER.info("Постраничный поиск задач, страница: {}, размер {}", page, size);
		return taskService.findByFilterAndPagination(PageRequest.of(page, size), flt)
				.map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
	}

    @Operation(summary = "Поиск задачи по идентификатору.")
	@GetMapping("/{id}")
	Mono<ResponseEntity<Task>> findById(@Parameter(description = "Идентификатор задачи.") @PathVariable Long id) {
    	LOGGER.info("Поиск задачи, идентификатор: {}.", id);
		return taskService.findById(id)
				.map(t -> ResponseEntity.ok(t)).defaultIfEmpty(ResponseEntity.notFound().build());
	}

    @Operation(summary = "Создание задачи.")
	@PostMapping
    Mono<ResponseEntity<Task>> create(@Parameter(description = "Набор данных для создания задачи.") @RequestBody TaskDto tsk) {
    	LOGGER.info("Создание задачи, идентификатор сотрудника создавшего задачу: {}.", tsk.fromEmployeeId());
		return taskService.create(tsk)
                .map(savedTsk -> ResponseEntity.status(HttpStatus.CREATED).body(savedTsk));
	}

	@DeleteMapping("/{id}")
    @Operation(summary = "Удаление задачи по идентификатору.")
    Mono<ResponseEntity<Void>> deleteById(@Parameter(description = "Идентификатор задачи.") @PathVariable Long id) {
    	LOGGER.info("Удаление задачи, идентификатор: {}.", id);
	    return taskService.findById(id)
	            .flatMap(c -> taskService.deleteById(c.getId())
	                .then(Mono.just(status(HttpStatus.ACCEPTED).<Void>build())))
	            .switchIfEmpty(Mono.just(notFound().build()));
	}
}
