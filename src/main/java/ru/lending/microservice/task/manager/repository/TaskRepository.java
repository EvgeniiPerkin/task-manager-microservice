package ru.lending.microservice.task.manager.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ru.lending.microservice.task.manager.entity.Task;

/**
 * Репозиторий для работы с данными задач
 */
@Repository
public interface TaskRepository extends ReactiveCrudRepository<Task, Long>  {}
