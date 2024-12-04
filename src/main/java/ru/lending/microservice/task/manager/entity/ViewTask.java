package ru.lending.microservice.task.manager.entity;

import java.time.OffsetDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(
  description = "Модель полей отображения списка задач.",
  title = "ViewTask",
  example = """
    {
        "id": 5,
    	"createdAt": "2024-11-21T10:31:45.436Z",
        "themeId": 1,
        "themeDesc": "Индикативная оценка недвижимости.",
        "priorityId": 3,
        "priorityDesc": "Высокий.",
        "conditionId": 1,
        "conditionDesc": "К выполнению.",
        "fromDepartmentId": 1,
        "fromEmployeeId": 6,
        "toDepartmentId": 3,
        "toEmployeeId": 43,
        "title": "Первая задача",
        "plannedStartDateTime": "2024-11-21T11:51:45.436Z",
        "plannedEndDateTime": "2024-11-22T11:51:45.436Z",
        "actualStartDateTime": "2024-11-21T11:51:45.436Z",
        "actualEndDateTime": "2024-11-22T11:51:45.436Z",
        "loanParameterId": 933
    }"""
  )
public class ViewTask {
  /**Идентификатор.*/
  @Schema(description = "Идентификатор задачи.", example = "5")
  @Id
  @Column("id")
  private Long id;

  /**Дата и время создания.*/
  @Schema(description = "Дата и время создания.", example = "2024-11-21T10:31:45.436Z")
  @Column("created_at")
  private OffsetDateTime createdAt;

  /**Тема.*/
  @Schema(description = "Идентификатор темы.", example = "5")
  @Column("theme_id")
  private Long themeId;
  @Schema(description = "Тема.", example = "Индикативная оценка недвижимости.") 
  @Column("theme_desc")
  private String themeDesc;

  /**Приоритет.*/
  @Schema(description = "Идентификатор приоритета.", example = "3")
  @Column("priority_id")
  private Long priorityId;
  @Schema(description = "Приоритет.", example = "Высокий.")
  @Column("priority_desc")
  private String priorityDesc;

  /**Состояние.*/
  @Schema(description = "Идентификатор состояния.", example = "1")
  @Column("condition_id")
  private Long conditionId;  
  @Schema(description = "Состояние.", example = "К выполнению.") 
  @Column("condition_desc")
  private String conditionDesc;

  /**Идентификатор отдела, который поставили задачу.*/
  @Schema(description = "Идентификатор отдела поставившего задачу.", example = "1")
  @Column("from_department_id")
  private Long fromDepartmentId;

  /**Идентификатор сотрудника создавшего задачу.*/
  @Schema(description = "Идентификатор сотрудника поставившего задачу.", example = "6")
  @Column("from_employee_id")
  private Long fromEmployeeId;

  /**Идентификатор отдела, которому поставили задачу.*/
  @Schema(description = "Идентификатор отдела, которому поставлена задача.", example = "3")
  @Column("to_department_id")
  private Long toDepartmentId;

  /**Идентификатор сотрудника выполняющиего\ответственного за задачу.*/
  @Schema(description = "Идентификатор сотрудника кому адресована задача.", example = "43")
  @Column("to_employee_id")
  private Long toEmployeeId;

  /**Заголовок задачи.*/
  @Schema(description = "Заголовок задачи.", example = "Поиск клеинта.")
  @Column("title")
  private String title;

  /**Планируемая дата и время начала выполнения задачи.*/
  @Schema(description = "Планируемая дата и время начала выполнения задачи.", example = "2024-11-21T10:31:45.436Z")
  @Column("planned_start_dt")
  private OffsetDateTime plannedStartDateTime;

  /**Планируемая дата и время завершения задачи.*/
  @Schema(description = "Планируемая дата и время заверщения выполнения задачи.", example = "2024-11-21T10:31:45.436Z")
  @Column("planned_end_dt")
  private OffsetDateTime plannedEndDateTime;

  /**Фактическая дата и время начала выполнения задачи.*/
  @Schema(description = "Фактическая дата и время ачала выполнения задачи.", example = "2024-11-21T10:31:45.436Z")
  @Column("actual_start_dt")
  private OffsetDateTime actualStartDateTime;

  /**Фактическая дата и время завершения задачи.*/
  @Schema(description = "Фактическая дата и время заверщения выполнения задачи.", example = "2024-11-21T10:31:45.436Z")
  @Column("actual_end_dt")
  private OffsetDateTime actualEndDateTime;

  /**Идентификатор параметров кредита*/
  @Schema(description = "Идентификатор параметров кредита.", example = "933")
  @Column("loan_parameter_id")
  private Long loanParameterId;
}
