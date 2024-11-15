package ru.lending.microservice.task.manager.entity.dto;

import java.time.OffsetDateTime;

/**
 * Контейнер данных задачи
 * Содержит набор полей для создания задачи
 */
public record TaskDto(
		/**
		 * Дата и время создания
		 */
		OffsetDateTime createdAt,
		/**
		 * Идентификатор темы
		 */
		Long themeId,
		/**
		 * Идентификатор приоритета
		 */
		Long priorityId,
		/**
		 * Идентификатор состояния
		 */
		Long conditionId,
		/**
		 * Идентификатор отдела поставившего задачу
		 */
		Long fromDepartmentId,
		/**
		 * Идентификатор сотрудника поставившего задачу
		 */
		Long fromEmployeeId,
		/**
		 * Идентификатор отдела, которому поставлена задача
		 */
		Long toDepartmentId,
		/**
		 * Идентификатор сотрудника, получившего задачу для выполнения
		 */
		Long toEmployeeId,
		/**
		 * Содержание задачи
		 */
		String content,
		/**
		 * Планируемая дата и время начала выполнения задачи
		 */
		OffsetDateTime plannedStartDateTime,
		/**
		 * Планируемая дата и время заверщения выполнения задачи
		 */
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
