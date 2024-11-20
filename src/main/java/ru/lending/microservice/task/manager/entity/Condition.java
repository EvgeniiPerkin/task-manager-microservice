package ru.lending.microservice.task.manager.entity;

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

/**
 * Состояние задачи
 */
@Table("conditions")
@Getter
@Setter
@ToString
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(
	description = "Структура данных состояния задач.", 
	title = "Condition", 
	example = """
		{
		  "id": "1",
		  "description": "Отменена"
		}"""
)
public class Condition {
    /**Идентификатор.*/
	@Schema(description = "Идентификатор состояния.", example = "1")
    @Id
    @Column("id")
    private Long id;

    /**Описание.*/
	@Schema(description = "Описание состояния.", example = "В работе")
    @Column("description")
    private String description;
}
