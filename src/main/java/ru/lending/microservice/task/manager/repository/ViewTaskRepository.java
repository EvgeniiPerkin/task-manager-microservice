package ru.lending.microservice.task.manager.repository;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ru.lending.microservice.task.manager.entity.ViewTask;

/**
 * Репозиторий для работы с данными вью задач
 * реализует ReactiveQueryByExampleExecutor для постраничного и фильтрационного поиска
 */
@Repository
public interface ViewTaskRepository extends ReactiveCrudRepository<ViewTask, Long>, ReactiveQueryByExampleExecutor<ViewTask> {}
