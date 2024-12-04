package ru.lending.microservice.task.manager.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import ru.lending.microservice.task.manager.entity.Theme;

/**
 * Репозиторий для работы с тем задач
 */
@Repository
public interface ThemeRepository extends ReactiveCrudRepository<Theme, Long> {
  /**
   * Поиск данных тем задач
   * @param id Идентификатор отдела
   * @return Список тем задач на конкретный отдел
   */
  @Query("SELECT t.* FROM tsk.themes t where t.department_id=:id")
  Flux<Theme> findByDepartmentId(Long id);
}
