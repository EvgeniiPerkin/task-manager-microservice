package ru.lending.microservice.task.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Theme;
import ru.lending.microservice.task.manager.entity.dto.ThemeDto;
import ru.lending.microservice.task.manager.exception.NotFoundException;
import ru.lending.microservice.task.manager.repository.ThemeRepository;
import ru.lending.microservice.task.manager.servce.ThemeService;

@Service
public class ThemeServiceImpl implements ThemeService {
	@Autowired
	private ThemeRepository repository;

	@Override
	public Flux<Theme> findByDepartmentId(Long id) {
		return repository.findByDepartmentId(id)
			.switchIfEmpty(Flux.error(new NotFoundException("Ресурс не найден -" + id)));
	}

	@Override
	public Mono<Void> deleteById(Long id) {
		return repository.deleteById(id);
	}

	@Override
	public Mono<Theme> create(@Valid Mono<ThemeDto> themeDto) {
		return themeDto
			.flatMap(t -> {
				Theme th = Theme.builder()
					.departmentId(t.departmentId())
					.description(t.description())
					.build();
				return repository.save(th);
			});
	}

	@Override
	public Mono<Theme> update(@Valid Mono<Theme> theme) {
		return theme
			.flatMap(t -> repository.save(t));
	}

	@Override
	public Mono<Theme> findById(Long id) {
		return repository.findById(id);
	}

}
