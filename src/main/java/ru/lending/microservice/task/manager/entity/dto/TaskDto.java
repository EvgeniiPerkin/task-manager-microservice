package ru.lending.microservice.task.manager.entity.dto;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Контейнер данных задачи
 * Содержит набор полей для создания задачи
 */
@Schema(
	description = "Модель полей для создания задачи.",
	title = "CreateTask",
	example = """
		{
			"createdAt": "2024-11-21T10:31:45.436Z",
		    "themeId": 5,
		    "priorityId": 3,
		    "conditionId": 1,
		    "fromDepartmentId": 1,
		    "fromEmployeeId": 6,
		    "toDepartmentId": 3,
		    "toEmployeeId": 43,
		    "title": "Первая задача",
		    "content": "Заполни данные пользователя.",
		    "plannedStartDateTime": "2024-11-21T11:51:45.436Z",
		    "plannedEndDateTime": "2024-11-22T11:51:45.436Z"
		}"""
)
public record TaskDto(
		/**
		 * Дата и время создания
		 */
	    @Schema(description = "Дата и время создания.", example = "2024-11-21T10:31:45.436Z")
		@NotNull(message = "Дата и время создания не может быть null.")
		OffsetDateTime createdAt,
		/**
		 * Идентификатор темы
		 */
	    @Schema(description = "Идентификатор темы.", example = "5")
		@NotNull(message = "Идентификатор темы не может быть null.")
		Long themeId,
		/**
		 * Идентификатор приоритета
		 */
	    @Schema(description = "Идентификатор приоритета.", example = "3")
		@NotNull(message = "Идентификатор приоритета не может быть null.")
		Long priorityId,
		/**
		 * Идентификатор состояния
		 */
	    @Schema(description = "Идентификатор состояния.", example = "1")
		@NotNull(message = "Идентификатор состояния не может быть null.")
		Long conditionId,
		/**
		 * Идентификатор отдела поставившего задачу
		 */
	    @Schema(description = "Идентификатор отдела поставившего задачу.", example = "1")
		@NotNull(message = "Идентификатор отдела поставившего задачу не может быть null.")
		Long fromDepartmentId,
		/**
		 * Идентификатор сотрудника поставившего задачу
		 */
	    @Schema(description = "Идентификатор сотрудника поставившего задачу.", example = "6")
		@NotNull(message = "Идентификатор сотрудника поставившего задачу не может быть null.")
		Long fromEmployeeId,
		/**
		 * Идентификатор отдела, которому поставлена задача
		 */
	    @Schema(description = "Идентификатор отдела, которому поставлена задача.", example = "3")
		@NotNull(message = "Идентификатор отдела, которому поставлена задача не может быть null.")
		Long toDepartmentId,
		/**
		 * Идентификатор сотрудника, получившего задачу для выполнения
		 */
	    @Schema(description = "Идентификатор сотрудника кому адресована задача.", example = "43")
		Long toEmployeeId,
		/**
		 * Заголовок задачи
		 */
	    @Schema(description = "Заголовок задачи.", example = "Поиск клеинта.")
		@NotBlank(message = "Строка заголовка не может быть пустой.")
		@Size(message = "Длина строки заголовка должна составлять от 1 до 100 символов.", min = 1, max = 100)
		String title,
		/**
		 * Содержание задачи
		 */
	    @Schema(description = "Содержание задачи.", example = "Попробуй найти этого клиента в базе.")
		@NotBlank(message = "Строка содержания не может быть пустой.")
		@Size(message = "Длина строки содержания должна составлять от 1 до 255 символов.", min = 1, max = 255)
		String content,
		/**
		 * Планируемая дата и время начала выполнения задачи
		 */
	    @Schema(description = "Планируемая дата и время начала выполнения задачи.", example = "2024-11-21T10:31:45.436Z")
		@NotNull(message = "Планируемая дата и время начала выполнения задачи не может быть null.")
		OffsetDateTime plannedStartDateTime,
		/**
		 * Планируемая дата и время заверщения выполнения задачи
		 */
	    @Schema(description = "Планируемая дата и время заверщения выполнения задачи.", example = "2024-11-21T10:31:45.436Z")
		@NotNull(message = "Планируемая дата и время заверщения выполнения задачи не может быть null.")
		OffsetDateTime plannedEndDateTime
		) {}
