package ru.lending.microservice.task.manager.entity;

import java.time.OffsetDateTime;

import org.springframework.data.annotation.Id;
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

@Table("tasks")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Task {
  /**Идентификатор.*/
  @Id
  @Column("id")
  private Long id;

  /**Дата и время создания.*/
  @Column("created_at")
  private OffsetDateTime createdAt;

  /**Тема.*/
  @Column("theme_id")
  private Long themeId;
  @Transient
  private Theme theme;

  /**Приоритет.*/
  @Column("priority_id")
  private Long priorityId;
  @Transient
  private Priority priority;

  /**Состояние.*/
  @Column("condition_id")
  private Long conditionId;    
  @Transient
  private Condition condition;

  /**Идентификатор отдела, который поставили задачу.*/
  @Column("from_department_id")
  private Long fromDepartmentId;

  /**Идентификатор сотрудника создавшего задачу.*/
  @Column("from_employee_id")
  private Long fromEmployeeId;

  /**Идентификатор отдела, которому поставили задачу.*/
  @Column("to_department_id")
  private Long toDepartmentId;

  /**Идентификатор сотрудника выполняющиего\ответственного за задачу.*/
  @Column("to_employee_id")
  private Long toEmployeeId;

  /**Заголовок задачи.*/
  @Column("title")
  private String title;

  /**Содержание\Описание задачи.*/
  @Column("content")
  private String content;

  /**Планируемая дата и время начала выполнения задачи.*/
  @Column("planned_start_dt")
  private OffsetDateTime plannedStartDateTime;

  /**Планируемая дата и время завершения задачи.*/
  @Column("planned_end_dt")
  private OffsetDateTime plannedEndDateTime;

  /**Фактическая дата и время начала выполнения задачи.*/
  @Column("actual_start_dt")
  private OffsetDateTime actualStartDateTime;

  /**Фактическая дата и время завершения задачи.*/
  @Column("actual_end_dt")
  private OffsetDateTime actualEndDateTime;

  /**Данные по параметрам кредита.*/
  @Transient
  private LoanParameter loanParameter;
}
