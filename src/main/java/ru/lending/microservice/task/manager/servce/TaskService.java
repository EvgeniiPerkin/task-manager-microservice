package ru.lending.microservice.task.manager.servce;

import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Task;
import ru.lending.microservice.task.manager.entity.ViewTask;
import ru.lending.microservice.task.manager.entity.dto.TaskDto;
import ru.lending.microservice.task.manager.entity.dto.ViewTaskDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface TaskService{
	Mono<Page<ViewTask>> findByFilterAndPagination(PageRequest pr, ViewTaskDto dto);
	Mono<Void> deleteById(Long id);
	Mono<Task> create(TaskDto taskDto);
	Mono<Task> update(Task task);
	Mono<Task> findById(Long id);
}
