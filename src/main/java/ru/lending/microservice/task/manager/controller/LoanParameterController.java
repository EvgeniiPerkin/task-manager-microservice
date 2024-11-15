package ru.lending.microservice.task.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.LoanParameter;
import ru.lending.microservice.task.manager.servce.LoanParameterService;

@RequestMapping("/api/v1/lp")
@RestController
public class LoanParameterController {
	@Autowired
	private LoanParameterService service;

	// потом нужно убрать,т.к. это действие будет выполняться при поиске задачи
	@GetMapping("/{taskId}/{loanParametersId}")
    @Operation(summary = "Поиск параметров кредита по идентификатору задачи и идентификатору параметров.")
	Mono<ResponseEntity<LoanParameter>> findById(
			@PathVariable Long taskId,
			@PathVariable Long loanParametersId) {
		return service.findByTaskIdAndLoanParameterId(taskId, loanParametersId)
				.map(t -> ResponseEntity.ok(t));
	}
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создание параметров кредита.")
    Mono<ResponseEntity<LoanParameter>> create(@RequestBody LoanParameter tsk) {
		return service.create(tsk)
                .map(savedTsk -> ResponseEntity.status(HttpStatus.CREATED).body(savedTsk));
	}
}
