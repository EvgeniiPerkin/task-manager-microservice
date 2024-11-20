package ru.lending.microservice.task.manager.controller;

import static org.springframework.http.ResponseEntity.ok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Priority;
import ru.lending.microservice.task.manager.servce.ReadService;

@Tag(name = "Приоритеты задач.", description = "Запросы к конроллеру приоритетов задач.")
@RequestMapping(value = "/api/v1/priorities", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class PriorityController {
	@Autowired
	private ReadService<Priority> service;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PriorityController.class);

    @Operation(
    	summary = "Получение всех приоритотов задач.",
    	responses = {
			@ApiResponse(
				responseCode = "200",
				description = "Возвращает список приоритетов задач.",
				content = @Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					array = @ArraySchema(
						schema = @Schema(
							name = "Priority",
							implementation = Priority.class
						)
					)
				)
			)
    	}
    )
	@GetMapping
	Mono<ResponseEntity<Flux<Priority>>> getAll() {
		LOGGER.info("Получение всех приоритеров задач.");
		return Mono.just(ok(service.getAll()));
	}
}
