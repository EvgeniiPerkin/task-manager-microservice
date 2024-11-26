package ru.lending.microservice.task.manager.exception;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
public class ApiErrorAttributes extends DefaultErrorAttributes {
	public ApiErrorAttributes() {
        super();
    }
	
	@Override
	public Map<String, Object> getErrorAttributes(ServerRequest request,
	    ErrorAttributeOptions options) {
		
		ArrayList<String> errors = new ArrayList<>();
		errors.add("Описание ошибки 1");
		errors.add("Описание ошибки 2");
		
	    var attributes = super.getErrorAttributes(request, options);
	    attributes.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
	    attributes.put("code", ErrorCode.UNEXPECTED.getCode());
	    attributes.put("listErrors", errors);
	    return attributes;
	}
}
