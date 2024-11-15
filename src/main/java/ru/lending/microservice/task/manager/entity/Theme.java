package ru.lending.microservice.task.manager.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

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
    private Long id;

    /**Идентификатор отдела.*/
    @Column("department_id")
    private Long departmentId;

    /**Описание темы.*/
    @Column("description")
    private String description;
}
