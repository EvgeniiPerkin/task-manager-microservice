package ru.lending.microservice.task.manager.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Тема задачи
 */
@Table("themes")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Theme {
    /**Идентификатор.*/
    @Id
    @Column("id")
	@NotNull(message = "{id.not.null}")
    private Long id;

    /**Идентификатор отдела.*/
    @Column("department_id")
	@NotNull(message = "{department.not.null}")
    private Long departmentId;

    /**Описание темы.*/
    @Column("description")
	@NotBlank(message = "{description.not.blank}")
	@Size(message = "{description.size}", min = 1, max = 255)
    private String description;
}
