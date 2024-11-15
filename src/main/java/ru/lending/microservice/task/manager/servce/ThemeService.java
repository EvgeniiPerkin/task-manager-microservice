package ru.lending.microservice.task.manager.servce;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Theme;
import ru.lending.microservice.task.manager.entity.dto.ThemeDto;

public interface ThemeService {
	Mono<Theme> create(ThemeDto themeDto);
	Flux<Theme> findByDepartmentId(Long id);
	Mono<Void> deleteById(Long id);
	Mono<Theme> update(Theme theme);
}
