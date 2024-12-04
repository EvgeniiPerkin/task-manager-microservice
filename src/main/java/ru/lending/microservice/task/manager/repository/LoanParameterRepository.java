package ru.lending.microservice.task.manager.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.LoanParameter;

/**
 * Репозиторий для работы с данными параметров кредита
 */
@Repository
public interface LoanParameterRepository  extends ReactiveCrudRepository<LoanParameter, Long> {
  /**
   * Поиск данных параметров кредита
   * @param taskId Идентификатор задачи
   * @return Параметры кредита
   */
  @Query("SELECT t.* FROM tsk.tasks_loan_parameters t where t.task_id=:taskId")
  Mono<LoanParameter> findByTaskId(Long taskId);

  Mono<LoanParameter> findByLoanParameterId(Long loanParameterId);
}
