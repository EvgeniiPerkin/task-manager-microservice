package ru.lending.microservice.task.manager.entity.dto;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Контейнер данных задачи
 * Содержит набор полей для создания задачи
 */
public record TaskDto(
		/**
		 * Дата и время создания
		 */
		@NotNull(message = "Дата и время создания не может быть null.")
		OffsetDateTime createdAt,
		/**
		 * Идентификатор темы
		 */
		@NotNull(message = "Идентификатор темы не может быть null.")
		Long themeId,
		/**
		 * Идентификатор приоритета
		 */
		@NotNull(message = "Идентификатор приоритета не может быть null.")
		Long priorityId,
		/**
		 * Идентификатор состояния
		 */
		@NotNull(message = "Идентификатор состояния не может быть null.")
		Long conditionId,
		/**
		 * Идентификатор отдела поставившего задачу
		 */
		@NotNull(message = "Идентификатор отдела поставившего задачу не может быть null.")
		Long fromDepartmentId,
		/**
		 * Идентификатор сотрудника поставившего задачу
		 */
		@NotNull(message = "Идентификатор сотрудника поставившего задачу не может быть null.")
		Long fromEmployeeId,
		/**
		 * Идентификатор отдела, которому поставлена задача
		 */
		@NotNull(message = "Идентификатор отдела, которому поставлена задача не может быть null.")
		Long toDepartmentId,
		/**
		 * Идентификатор сотрудника, получившего задачу для выполнения
		 */
		Long toEmployeeId,
		/**
		 * Заголовок задачи
		 */
		@NotBlank(message = "Строка заголовка не может быть пустой.")
		@Size(message = "Длина строки заголовка должна составлять от 1 до 100 символов.", min = 1, max = 100)
		String title,
		/**
		 * Содержание задачи
		 */
		@NotBlank(message = "Строка содержания не может быть пустой.")
		@Size(message = "Длина строки содержания должна составлять от 1 до 255 символов.", min = 1, max = 255)
		String content,
		/**
		 * Планируемая дата и время начала выполнения задачи
		 */
		@NotNull(message = "Планируемая дата и время начала выполнения задачи не может быть null.")
		OffsetDateTime plannedStartDateTime,
		/**
		 * Планируемая дата и время заверщения выполнения задачи
		 */
		@NotNull(message = "Планируемая дата и время заверщения выполнения задачи не может быть null.")
		OffsetDateTime plannedEndDateTime,
		/**
		 * Фактическая дата и время начала выполнения задачи
		 */
		OffsetDateTime actualStartDateTime,
		/**
		 * Фактическая дата и время заверщения выполнения задачи
		 */
		OffsetDateTime actualEndDateTime
		) {}
