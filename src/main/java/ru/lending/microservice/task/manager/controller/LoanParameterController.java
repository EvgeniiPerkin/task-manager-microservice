package ru.lending.microservice.task.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
import ru.lending.microservice.task.manager.exception.Error;
import ru.lending.microservice.task.manager.entity.LoanParameter;
import ru.lending.microservice.task.manager.servce.LoanParameterService;

@Tag(name = "Парметры кредита.", description = "Запросы к конроллеру параметров кредита.")
@RequestMapping(value = "/api/v1/lp", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class LoanParameterController {
	@Autowired
	private LoanParameterService service;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoanParameterController.class);

    @Operation(
    	summary = "Создание параметров кредита.",
    	responses = {
			@ApiResponse(
				responseCode = "201",
				description = "Возвращает параметры кредита (идентификатор параметров"
						+ "кредита и связанные с ним "
						+ "идентификаторы клиентов и идентификаторы обеспечений).",
				content = @Content(
					mediaType = MediaType.APPLICATION_JSON_VALUE,
					array = @ArraySchema(
						schema = @Schema(
							name = "LoanParameter",
							implementation = LoanParameter.class
						)
					)
				)
			),
            @ApiResponse(
        		responseCode = "409",
              	description = "Не удается создать запись по заданным параметрам, т.к. запись уже существует.",
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
	@PostMapping
    Mono<ResponseEntity<LoanParameter>> create(
		@Parameter(description = "Параметры кредита.")
		@Valid
		@RequestBody Mono<LoanParameter> lp) {
    	return service.create(lp)
    		.doOnNext(l -> LOGGER.info("Создание параметров кредита, идентификатор парметров: {}, идентификатор задачи: {}.",
				l.getLoanParameterId(), l.getTaskId()))
    		.map(savedLP -> ResponseEntity.status(HttpStatus.CREATED).body(savedLP));
	}
}
