package ru.lending.microservice.task.manager.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ru.lending.microservice.task.manager.entity.Priority;

/**
 * Репозиторий для работы с приоритетами задач
 */
@Repository
public interface PriorityRepository  extends ReactiveCrudRepository<Priority, Long> {}
