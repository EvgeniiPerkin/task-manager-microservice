package ru.lending.microservice.task.manager.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import ru.lending.microservice.task.manager.entity.Collateral;

/**
 * Репозиторий для работы с данными обеспечений
 */
@Repository
public interface CollateralRepository extends ReactiveCrudRepository<Collateral, Long> {
	/**
	 * Поиск данных обеспечений (идентификаторов обеспечений)
	 * @param taskId Идентификатор задачи
	 * @param loanParameterId Идентификатор пармаметров кредита
	 * @return Список обеспечений
	 */
	@Query("SELECT t.* FROM tsk.loan_parameters_collaterals t where t.task_id=:taskId and t.loan_parameter_id=:loanParameterId")
	Flux<Collateral> findByTaskIdAndLoanParameterId(Long taskId, Long loanParameterId);
}
