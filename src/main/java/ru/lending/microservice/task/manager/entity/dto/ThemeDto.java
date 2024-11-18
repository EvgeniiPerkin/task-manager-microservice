package ru.lending.microservice.task.manager.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Контейнер данных темы задачи
 * Содержит набор полей для создания темы задачи
 */
public record ThemeDto(
		/**
		 * Идентификатор задачи
		 */
		@NotNull(message = "Идентификатор отдела не может быть null.")
		Long departmentId,
		/**
		 * Описание
		 */
		@NotBlank(message = "Строка описания не может быть пустой.")
		@Size(message = "Длина строки описания должна составлять от 1 до 255 символов.", min = 1, max = 255)
		String description
		) {}
