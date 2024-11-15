package ru.lending.microservice.task.manager.repository;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ru.lending.microservice.task.manager.entity.Task;

@Repository
public interface TaskRepository extends ReactiveCrudRepository<Task, Long>, ReactiveQueryByExampleExecutor<Task>  {}
