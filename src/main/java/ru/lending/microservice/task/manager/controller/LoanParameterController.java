package ru.lending.microservice.task.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.LoanParameter;
import ru.lending.microservice.task.manager.servce.LoanParameterService;

@Tag(name = "Парметры кредита.", description = "Запросы к конроллеру параметров кредита.")
@RequestMapping("/api/v1/lp")
@RestController
public class LoanParameterController {
	@Autowired
	private LoanParameterService service;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoanParameterController.class);

    @Operation(summary = "Создание параметров кредита.")
	@PostMapping
    Mono<ResponseEntity<LoanParameter>> create(@Parameter(description = "Параметры кредита.") @RequestBody LoanParameter lp) {
    	LOGGER.info("Создание параметров кредита, идентификатор парметров: {}, идентификатор задачи: {}.", lp.getLoanParameterId(), lp.getTaskId());
		return service.create(lp)
                .map(savedLP -> ResponseEntity.status(HttpStatus.CREATED).body(savedLP));
	}
}
