package ru.lending.microservice.task.manager.servce;

import reactor.core.publisher.Flux;

public interface ReadService<T> {
	Flux<T> getAll();
}
