package ru.lending.microservice.task.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Flux;
import ru.lending.microservice.task.manager.entity.Condition;
import ru.lending.microservice.task.manager.servce.ReadService;

@RequestMapping("/api/v1/conditions")
@RestController
public class ConditionController {
	@Autowired
	private ReadService<Condition> service;

	@GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение всех состояний задач.")
	Flux<Condition> getAll() {
		return service.getAll();
	}
}
