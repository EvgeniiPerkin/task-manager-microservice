package ru.lending.microservice.task.manager.exception;

import static ru.lending.microservice.task.manager.exception.ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION;
import static ru.lending.microservice.task.manager.exception.ErrorCode.RESOURCE_NOT_FOUND;
import static ru.lending.microservice.task.manager.exception.ErrorCode.GENERIC_ALREADY_EXISTS;

import java.util.Map;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;

import reactor.core.publisher.Mono;

@Component
@Order(-2)
public class ApiErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {
	public ApiErrorWebExceptionHandler(ApiErrorAttributes errorAttributes,
		    ApplicationContext applicationContext,
		    ServerCodecConfigurer serverCodecConfigurer) {
		super(errorAttributes, new WebProperties().getResources(), applicationContext);
		super.setMessageWriters(serverCodecConfigurer.getWriters());
		super.setMessageReaders(serverCodecConfigurer.getReaders());
	}
	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(), this::handleErrorResponse);
	}
	
	private Mono<ServerResponse> handleErrorResponse(ServerRequest request) {
	    Map<String, Object> errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        Throwable throwable = (Throwable) request
            .attribute("org.springframework.boot.web.reactive.error.DefaultErrorAttributes.ERROR")
            .orElseThrow(() -> new IllegalStateException("Missing exception attribute in ServerWebExchange"));
        throwable.printStackTrace();
        ErrorCode code = ErrorCode.GENERIC_ERROR;

        if (throwable instanceof IllegalArgumentException
            || throwable instanceof DataIntegrityViolationException
            || throwable instanceof ServerWebInputException) {
            code = ILLEGAL_ARGUMENT_EXCEPTION;
        } else if (throwable instanceof ResourceNotFoundException) {
            code = RESOURCE_NOT_FOUND;
        } else if (throwable instanceof GenericAlreadyExistsException) {
        	code = GENERIC_ALREADY_EXISTS;
        }

        switch (code) {
	        case ILLEGAL_ARGUMENT_EXCEPTION -> {
	            errorPropertiesMap.put("status", HttpStatus.BAD_REQUEST);
	            errorPropertiesMap.put("code", ILLEGAL_ARGUMENT_EXCEPTION.getCode());
	            errorPropertiesMap.put("error", ILLEGAL_ARGUMENT_EXCEPTION);
	            errorPropertiesMap.put("message", String
	                .format("%s %s", ILLEGAL_ARGUMENT_EXCEPTION.getKey(),
	                    throwable.getMessage()));
	            return ServerResponse.status(HttpStatus.BAD_REQUEST)
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(BodyInserters.fromValue(errorPropertiesMap));
	        }
	        case RESOURCE_NOT_FOUND -> {
	            errorPropertiesMap.put("status", HttpStatus.NOT_FOUND);
	            errorPropertiesMap.put("code", RESOURCE_NOT_FOUND.getCode());
	            errorPropertiesMap.put("error", RESOURCE_NOT_FOUND);
	            errorPropertiesMap.put("message", String
	                .format("%s %s", RESOURCE_NOT_FOUND.getKey(),
	                    throwable.getMessage()));
	            return ServerResponse.status(HttpStatus.NOT_FOUND)
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(BodyInserters.fromValue(errorPropertiesMap));
	        }
			case GENERIC_ALREADY_EXISTS -> {
		        errorPropertiesMap.put("status", HttpStatus.NOT_ACCEPTABLE);
		        errorPropertiesMap.put("code", GENERIC_ALREADY_EXISTS.getCode());
		        errorPropertiesMap.put("error", GENERIC_ALREADY_EXISTS);
		        errorPropertiesMap.put("message", String
		            .format("%s %s", GENERIC_ALREADY_EXISTS.getKey(),
		                throwable.getMessage()));
		        return ServerResponse.status(HttpStatus.NOT_ACCEPTABLE)
		            .contentType(MediaType.APPLICATION_JSON)
		            .body(BodyInserters.fromValue(errorPropertiesMap));
	        }
		    default -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
		        .contentType(MediaType.APPLICATION_JSON)
		        .body(BodyInserters.fromValue(errorPropertiesMap));
        }
        
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorPropertiesMap));
	}
}
