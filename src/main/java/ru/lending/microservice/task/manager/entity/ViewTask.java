package ru.lending.microservice.task.manager.entity;

import java.time.OffsetDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Table("view_tasks")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ViewTask {
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
    @Column("theme_desc")
    private String themeDesc;

    /**Приоритет.*/
    @Column("priority_id")
    private Long priorityId;
    @Column("priority_desc")
    private String priorityDesc;

    /**Состояние.*/
    @Column("condition_id")
    private Long conditionId;   
    @Column("condition_desc")
    private String conditionDesc;

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
    
    /**Идентификатор параметров кредита*/
    @Column("loan_parameter_id")
    private Long loanParameterId;
}
