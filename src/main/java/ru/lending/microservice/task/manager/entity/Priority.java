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
 * Приоритет задачи
 */
@Table("priorities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@Builder
@Schema(
	description = "Структура данных приоритета задач.", 
	title = "Priority", 
	example = """
		{
		  "id": "1",
		  "description": "Высокий"
		}"""
)
public class Priority {
    /**Идентификатор.*/
	@Schema(description = "Идентификатор приоритета.", example = "1")
    @Id
    @Column("id")
    private Long id;

    /**Описание.*/
	@Schema(description = "Описание приоритета.", example = "Высокий")
    @Column("description")
    private String description;
}
