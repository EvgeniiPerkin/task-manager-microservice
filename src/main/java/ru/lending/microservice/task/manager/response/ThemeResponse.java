package ru.lending.microservice.task.manager.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import ru.lending.microservice.task.manager.entity.Theme;
import ru.lending.microservice.task.manager.exception.Error;

@Getter
@Setter
public class ThemeResponse {
	private Theme theme;
    private List<Error> errors;
}
