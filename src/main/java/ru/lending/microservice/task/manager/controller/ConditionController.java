package ru.lending.microservice.task.manager.controller;

import static org.springframework.http.ResponseEntity.ok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Condition;
import ru.lending.microservice.task.manager.servce.ReadService;

@Tag(name = "Состояния задач.", description = "Запросы к конроллеру состояний задач.")
@RequestMapping("/api/v1/conditions")
@RestController
public class ConditionController {
	@Autowired
	private ReadService<Condition> service;

	private static final Logger LOGGER = LoggerFactory.getLogger(ConditionController.class);

    @Operation(summary = "Получение всех состояний задач.")
	@GetMapping
	Mono<ResponseEntity<Flux<Condition>>> getAll() {
		LOGGER.info("Получение всех состояний задач.");
		return Mono.just(ok(service.getAll()));
	}
}
