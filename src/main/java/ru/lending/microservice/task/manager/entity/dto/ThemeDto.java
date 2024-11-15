package ru.lending.microservice.task.manager.entity.dto;

/**
 * Контейнер данных темы задачи
 * Содержит набор полей для создания темы задачи
 */
public record ThemeDto(
		/**
		 * Идентификатор задачи
		 */
		Long departmentId,
		/**
		 * Описание
		 */
		String description
		) {}
