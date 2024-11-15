package ru.lending.microservice.task.manager.servce;

import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.LoanParameter;

public interface LoanParameterService {
	Mono<LoanParameter> findByTaskIdAndLoanParameterId(Long taskId, Long loanParameterId);
	Mono<LoanParameter> create(LoanParameter lp);
}
