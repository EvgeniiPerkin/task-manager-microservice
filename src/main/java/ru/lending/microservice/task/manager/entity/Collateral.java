package ru.lending.microservice.task.manager.entity;

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
 * Данные обеспечения (связь параметров кредита с обеспечением)
 */
@Table("loan_parameters_collaterals")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@Builder
public class Collateral {
    /**Идентификатор клиента.*/
    @Column("collateral_id")
    private Long collateralId;

    /**Идентификатор парметров кредита.*/
    @Column("loan_parameter_id")
    private Long loanParameterId;

    /**Идентификатор задачи.*/
    @Column("task_id")
    private Long taskId;
}
