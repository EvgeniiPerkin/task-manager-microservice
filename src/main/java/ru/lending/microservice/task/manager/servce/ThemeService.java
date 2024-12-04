package ru.lending.microservice.task.manager.servce;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Theme;

/**
 * Сервис обработки данных тем задачd
 */
public interface ThemeService {
  /**
   * Создает новую тему задачи
   * @param t Набор данных для создания темы задачи
   * @return Созданная тема задачи
   */
  Mono<Theme> create(@Valid Mono<Theme> t);
  /**
   * Поиск темы задачи по идентификатор отдела
   * @param id идентификатор отдела
   * @return Список тем задач на конкретный отдел
   */
  Flux<Theme> findByDepartmentId(Long id);
  /**
   * Поиск темы задачи по ее идентификатору
   * @param id идентификатор задачи
   * @return Тема задачи
   */
  Mono<Theme> findById(Long id);
  /**
   * Удаляет тему задачи
   * @param id Идентификатор задачи
   * @return
   */
  Mono<Void> deleteById(Long id);
  /**
   * Изменяет данные темы задачи
   * @param t Тама задачи
   * @return Измененная тема задачи
   */
  Mono<Theme> update(@Valid Mono<Theme> t);
}
