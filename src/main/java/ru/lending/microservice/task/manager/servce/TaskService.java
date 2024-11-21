package ru.lending.microservice.task.manager.servce;

import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Task;
import ru.lending.microservice.task.manager.entity.ViewTask;
import ru.lending.microservice.task.manager.entity.dto.FilterTaskDto;
import ru.lending.microservice.task.manager.entity.dto.TaskDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import jakarta.validation.Valid;

/**
 * Сервис обработки данных задач
 */
public interface TaskService{
	/**
	 * Поиск вью задач постранично с применением фильтраций
	 * @param pr Данные о пагинации
	 * @param flt Данные для фильтрации
	 * @return Страницы\у с вью задачами
	 */
	Mono<Page<ViewTask>> findByFilterAndPagination(PageRequest pr, FilterTaskDto flt);
	/**
	 * Удаляет задачу по ее идентификатору
	 * @param id Идентификатор задачи
	 * @return
	 */
	Mono<Void> deleteById(Long id);
	/**
	 * Создает новую задачу
	 * @param t Набор данных для создания новой задачи
	 * @return Созданная задача
	 */
	Mono<Task> create(@Valid Mono<TaskDto> t);
	/**
	 * Обновляет данные задачи
	 * @param task Данные задачи
	 * @return Обновленные данные задачи
	 */
	Mono<Task> update(Task task);
	/**
	 * Поиск задачи по ее идентификатору
	 * @param id Идентификатор задачи
	 * @return Задача
	 */
	Mono<Task> findById(Long id);
}
