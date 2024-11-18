package ru.lending.microservice.task.manager.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.support.WebExchangeBindException;

import ru.lending.microservice.task.manager.exception.Error;
import ru.lending.microservice.task.manager.entity.Theme;
import ru.lending.microservice.task.manager.entity.dto.ThemeDto;

public class ThemeMapper {
	private ThemeMapper() {
    }

    public static Theme fromWebExchangeBindException(WebExchangeBindException ex) {
    	Theme res = new Theme();
        List<Error> errors = ex.getFieldErrors().stream()
                .map(fieldError -> new Error(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        res.setErrors(errors);
        return res;
    }
    // TODO Додумать обработку ошибок и валидацию, возможно нужно обернуть ответ в какой либо класс содержащий список ошибок.
}
