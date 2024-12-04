package ru.lending.microservice.task.manager.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Контейнер данных темы задачи
 * Содержит набор полей для создания темы задачи
 */
@Schema(
  description = "Модель темы задачи.",
  title = "Theme",
  example = """
    {
        "departmentId": 1,
        "description": "Индикативная оценка недвижимости."
    }"""
  )
public record ThemeDto(
  /**
   * Идентификатор отдела
   */
  @Schema(description = "Идентификатор отдела.", example = "1")
  @NotNull(message = "{department.not.null}")
  Long departmentId,
  /**
   * Описание
   */
  @Schema(description = "Описание.", example = "Индикативная оценка недвижимости.")
  @NotBlank(message = "{description.not.blank}")
  @Size(message = "{description.size}", min = 1, max = 255)
  String description
  ) {}
