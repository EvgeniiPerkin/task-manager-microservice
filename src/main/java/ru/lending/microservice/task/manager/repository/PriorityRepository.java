package ru.lending.microservice.task.manager.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ru.lending.microservice.task.manager.entity.Priority;

@Repository
public interface PriorityRepository  extends ReactiveCrudRepository<Priority, Long> {}
