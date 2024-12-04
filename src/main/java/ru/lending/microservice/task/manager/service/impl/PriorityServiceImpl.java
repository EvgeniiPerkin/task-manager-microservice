package ru.lending.microservice.task.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import ru.lending.microservice.task.manager.entity.Priority;
import ru.lending.microservice.task.manager.repository.PriorityRepository;
import ru.lending.microservice.task.manager.servce.ReadService;

@Service
public class PriorityServiceImpl implements ReadService<Priority>{
  @Autowired
  private PriorityRepository repository;

  @Override
  public Flux<Priority> getAll() {
    return repository.findAll();
  }
}
