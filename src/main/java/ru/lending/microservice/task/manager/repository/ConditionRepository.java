package ru.lending.microservice.task.manager.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ru.lending.microservice.task.manager.entity.Condition;

@Repository
public interface ConditionRepository extends ReactiveCrudRepository<Condition, Long> {}
