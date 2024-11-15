package ru.lending.microservice.task.manager.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import ru.lending.microservice.task.manager.entity.Client;

@Repository
public interface ClientRepository  extends ReactiveCrudRepository<Client, Long> {
	@Query("SELECT t.* FROM tsk.loan_parameters_clients t where t.task_id=:taskId and t.loan_parameter_id=:loanParameterId")
	Flux<Client> findByTaskIdAndLoanParameterId(Long taskId, Long loanParameterId);
}
