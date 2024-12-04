package ru.lending.microservice.task.manager.servce;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.LoanParameter;

/**
 * Сервис обработки данных параметров кредита
 */
public interface LoanParameterService {
  /**
   * Создает запись в бд параметров кредита
   * @param lp параметры кредита
   * @return Созданная запись параметров кредита
   */
  Mono<LoanParameter> create(@Valid Mono<LoanParameter> lp);

  /**
   * поиск параметров кредита по идентификатору
   * @param loanParameterId идентификатор параметров кредита
   * @return параметры кредита
   */
  Mono<LoanParameter> findByLoanParameterId(Long loanParameterId);
}
