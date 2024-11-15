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
 * Данные клиента (связь параметров кредита с клиентом)
 */
@Table("loan_parameters_clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@Builder
public class Client {
    /**Идентификатор клиента.*/
    @Column("client_id")
    private Long clientId;

    /**Идентификатор парметров кредита.*/
    @Column("loan_parameter_id")
    private Long loanParameterId;

    /**Идентификатор задачи.*/
    @Column("task_id")
    private Long taskId;
}
