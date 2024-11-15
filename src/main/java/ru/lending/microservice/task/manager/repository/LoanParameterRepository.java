package ru.lending.microservice.task.manager.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.LoanParameter;

@Repository
public interface LoanParameterRepository  extends ReactiveCrudRepository<LoanParameter, Long> {
	@Query("SELECT t.* FROM tsk.tasks_loan_parameters t where t.task_id=:taskId and t.loan_parameter_id=:loanParameterId")
	Mono<LoanParameter> findByTaskIdAndLoanParameterId(Long taskId, Long loanParameterId);
}
