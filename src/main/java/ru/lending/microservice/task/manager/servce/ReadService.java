package ru.lending.microservice.task.manager.servce;

import reactor.core.publisher.Flux;

/**
 * Сервис чтения данных какой либо entity
 * @param <T> entity
 */
public interface ReadService<T> {
  /**
   * Получение всех данных какой либо сущности
   * @return список сущностей
   */
  Flux<T> getAll();
}
