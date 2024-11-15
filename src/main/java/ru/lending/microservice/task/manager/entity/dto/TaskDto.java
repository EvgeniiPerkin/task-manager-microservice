package ru.lending.microservice.task.manager.entity.dto;

import java.time.OffsetDateTime;

/**
 * 
 */
public record TaskDto(
		OffsetDateTime createdAt,
		Long themeId,
		Long priorityId,
		Long conditionId,
		Long fromDepartmentId,
		Long fromEmployeeId,
		Long toDepartmentId,
		Long toEmployeeId,
		String content,
		OffsetDateTime plannedStartDateTime,
		OffsetDateTime plannedEndDateTime,
		OffsetDateTime actualStartDateTime,
		OffsetDateTime actualEndDateTime
		) {}
