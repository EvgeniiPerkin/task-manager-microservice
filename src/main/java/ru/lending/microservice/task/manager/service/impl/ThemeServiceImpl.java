package ru.lending.microservice.task.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Theme;
import ru.lending.microservice.task.manager.entity.dto.ThemeDto;
import ru.lending.microservice.task.manager.repository.ThemeRepository;
import ru.lending.microservice.task.manager.servce.ThemeService;

@Service
public class ThemeServiceImpl implements ThemeService {
	@Autowired
	private ThemeRepository repository;

	@Override
	public Flux<Theme> findByDepartmentId(Long id) {
		return repository.findByDepartmentId(id);
	}

	@Override
	public Mono<Void> deleteById(Long id) {
		return repository.deleteById(id);
	}

	@Override
	public Mono<Theme> create(ThemeDto themeDto) {
		Theme t = Theme.builder()
				.departmentId(themeDto.departmentId())
				.description(themeDto.description())
				.build();
		return repository.save(t);
	}

	@Override
	public Mono<Theme> update(Theme theme) {
		return repository.save(theme);
	}

}
