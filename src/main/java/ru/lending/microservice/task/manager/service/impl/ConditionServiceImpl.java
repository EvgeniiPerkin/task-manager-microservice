package ru.lending.microservice.task.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import ru.lending.microservice.task.manager.entity.Condition;
import ru.lending.microservice.task.manager.repository.ConditionRepository;
import ru.lending.microservice.task.manager.servce.ReadService;

@Service
public class ConditionServiceImpl implements ReadService<Condition> {
  @Autowired
  private ConditionRepository repository;

  @Override
  public Flux<Condition> getAll() {
    return repository.findAll();
  }
}
