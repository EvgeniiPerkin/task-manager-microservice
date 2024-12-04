package ru.lending.microservice.task.manager.controller;

import static org.springframework.http.ResponseEntity.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Task;
import ru.lending.microservice.task.manager.entity.ViewTask;
import ru.lending.microservice.task.manager.entity.dto.FilterTaskDto;
import ru.lending.microservice.task.manager.entity.dto.TaskDto;
import ru.lending.microservice.task.manager.exception.Error;
import ru.lending.microservice.task.manager.exception.NotFoundException;
import ru.lending.microservice.task.manager.servce.TaskService;

@Tag(name = "Задачи.", description = "Запросы к конроллеру задач.")
@RequestMapping(value = "/api/v1/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class TaskController {
  @Autowired
  private TaskService taskService;

  private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

  @Operation(
    summary = "Постраничный поиск задач с фильтрацией.",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "Возвращает список отфильтрованных задач.")
    }
  )
  @GetMapping
  Mono<ResponseEntity<Page<ViewTask>>> findByFilterAndPagination(
    @Parameter(description = "Номер страницы.") 
    @RequestParam(required = false, defaultValue = "0") Integer page, 
    @Parameter(description = "Кол-во задач на странице.") 
    @RequestParam(required = false, defaultValue = "10") Integer size,
    @Parameter(description = "Данные для фильтрации.") FilterTaskDto flt) {
    LOGGER.info("Постраничный поиск задач, страница: {}, размер {}", page, size);
    return taskService.findByFilterAndPagination(PageRequest.of(page, size), flt)
      .map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Operation(
    summary = "Поиск задачи по идентификатору.",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "Возвращает задачу.",
        content = @Content(
          mediaType = MediaType.APPLICATION_JSON_VALUE,
          array = @ArraySchema(
            schema = @Schema(
              name = "Task",
              implementation = Task.class
              )
            )
          )
        ),
      @ApiResponse(
        responseCode = "404",
        description = "Не удается найти данные задачи.",
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
  @GetMapping("/{id}")
  Mono<ResponseEntity<Task>> findById(
    @Parameter(description = "Идентификатор задачи.")
    @RequestParam(required = true) Long id) {
    LOGGER.info("Поиск задачи, идентификатор: {}.", id);
    return taskService.findById(id)
      .map(t -> ResponseEntity.ok(t))
      .switchIfEmpty(Mono.error(new NotFoundException("Задача не найдена.")));
  }

  @Operation(
    summary = "Создание задачи.",
    responses = {
      @ApiResponse(
        responseCode = "201",
        description = "Возвращает созданную задачу.",
        content = @Content(
          mediaType = MediaType.APPLICATION_JSON_VALUE,
          array = @ArraySchema(
            schema = @Schema(
              name = "LoanParameter",
              implementation = Task.class
              )
            )
          )
        )
    }
    )
  @PostMapping
  Mono<ResponseEntity<Task>> create(
    @Parameter(description = "Набор данных для создания задачи.")
    @Valid
    @RequestBody Mono<TaskDto> tsk) {    	
    return taskService.create(tsk)
      .doOnNext(t -> LOGGER.info("Создание задачи, идентификатор: {}.", t.getId()))
      .map(savedTsk -> ResponseEntity.status(HttpStatus.CREATED).body(savedTsk));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Удаление задачи по идентификатору.",
  responses = {
    @ApiResponse(
      responseCode = "202",
      description = "Задача удалена."
      ),
    @ApiResponse(
      responseCode = "404",
      description = "Не удается удалить данные задачи, т.к. запись отсутствует.",
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
  Mono<ResponseEntity<Void>> deleteById(
    @Parameter(description = "Идентификатор задачи.")
    @RequestParam(required = true) Long id) {
    LOGGER.info("Удаление задачи, идентификатор: {}.", id);
    return taskService.findById(id)
      .flatMap(c -> taskService.deleteById(c.getId())
        .then(Mono.just(status(HttpStatus.ACCEPTED).<Void>build())))
      .switchIfEmpty(Mono.error(new NotFoundException("Удаление задачи. Задача не найдена.")));
  }
}
