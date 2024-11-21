package ru.lending.microservice.task.manager.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Контейнер данных для фильтрации задач
 */
@Schema(
	description = "Модель полей для фильтрации задач.",
	title = "FilterTask",
	example = """
		{
		    "themeId": 5,
		    "priorityId": 3,
		    "conditionId": 1,
		    "fromDepartmentId": 1,
		    "fromEmployeeId": 6,
		    "toDepartmentId": 3,
		    "toEmployeeId": 43,
		}"""
)
public record FilterTaskDto(
		/**
		 * фильтр по теме задач, используем если значение больше нуля
		 */
	    @Schema(description = "Идентификатор темы.", example = "5")
		Long themeId,
		/**
		 * фильтр приоритету задач, используем если значение больше нуля
		 */
	    @Schema(description = "Идентификатор приоритета.", example = "3")
		Long priorityId,
		/**
		 * фильтр по состоянию задач, используем если значение больше нуля
		 */
	    @Schema(description = "Идентификатор состояния.", example = "1")
		Long conditionId,
		/**
		 * фильтр по отделу создавшему задачи, используем если значение больше нуля
		 */
	    @Schema(description = "Идентификатор отделу создавшего задачу.", example = "1")
		Long fromDepartmentId,
		/**
		 * фильтр по сотруднику создавшему задачи, используем если значение больше нуля
		 */
	    @Schema(description = "Идентификатор сотрудника создавшего задачу.", example = "6")
		Long fromEmployeeId,
		/**
		 * фильтр по отделу получившему задачи, используем если значение больше нуля
		 */
	    @Schema(description = "Идентификатор отдела получившего задачу.", example = "3")
		Long toDepartmentId,
		/**
		 * фильтр по сотруднику получившему задачи, используем если значение больше нуля
		 */
	    @Schema(description = "Идентификатор сотрудника кому адресована задача.", example = "43")
		Long toEmployeeId
		) {}
