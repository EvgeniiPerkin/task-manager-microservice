package ru.lending.microservice.task.manager.entity;

import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Параметры кредита (данные связи парметров кредита с задачей)
 */
@Table("tasks_loan_parameters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@Builder
public class LoanParameter {
  /**Идентификатор задачи.*/
  @Column("task_id")
  private Long taskId;

  /**Идентификатор парметров кредита.*/
  @Column("loan_parameter_id")
  private Long loanParameterId;

  @Builder.Default
  @Transient
  List<Client> clients = Collections.emptyList();

  @Builder.Default
  @Transient
  List<Collateral> collaterals = Collections.emptyList();
}
