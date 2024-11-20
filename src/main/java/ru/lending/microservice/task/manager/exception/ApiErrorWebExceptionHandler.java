package ru.lending.microservice.task.manager.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

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
		return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
	}
	
	private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
	    Map<String, Object> errorProperties = getErrorAttributes(request, ErrorAttributeOptions.defaults());
	    
	    Throwable throwable = (Throwable) request
		    .attribute("org.springframework.boot.web.reactive.error.DefaultErrorAttributes.ERROR")
		    .orElseThrow(() -> new IllegalStateException("Missing exception attribute in ServerWebExchange"));
		
		if (throwable instanceof WebExchangeBindException) {
			return render((WebExchangeBindException) throwable, request, errorProperties);
	    }
		
		if (throwable instanceof CustomBaseException) {
			return render((CustomBaseException) throwable, request, errorProperties);
		}
		
		return renderDefault(throwable, request, errorProperties);
	}

	private Mono<ServerResponse> render(
			WebExchangeBindException exception,
			ServerRequest request,
			Map<String, Object> errorProperties) {
    	
		var errors = exception.getBindingResult()
	      .getAllErrors()
	      .stream()
	      .map(DefaultMessageSourceResolvable::getDefaultMessage)
	      .collect(Collectors.toList());
		
		var code = ErrorCode.VALIDATION_FAIL;

	    errorProperties.put("status", code.getStatus());
	    errorProperties.put("code", code.getCode());
	    errorProperties.put("error", "Ошибка валидации данных.");
	    errorProperties.put("listErrors", errors);
	    return ServerResponse
	        .status(code.getStatus())
	        .contentType(MediaType.APPLICATION_JSON)
	        .body(BodyInserters.fromValue(errorProperties));
	}
	
	private Mono<ServerResponse> render(
			CustomBaseException exception,
			ServerRequest request,
			Map<String, Object> errorProperties) {

		var code = exception.getErrorCode();

	    errorProperties.put("status", code.getStatus());
	    errorProperties.put("code", code.getCode());
	    errorProperties.put("error", "Ошибка.");
	    errorProperties.put("listErrors", exception.getMessage());
	    
	    return ServerResponse
	        .status(code.getStatus())
	        .contentType(MediaType.APPLICATION_JSON)
	        .body(BodyInserters.fromValue(errorProperties));
	}
	
	private Mono<ServerResponse> renderDefault(
			Throwable throwable,
			ServerRequest request,
			Map<String, Object> errorProperties) {

		var code = ErrorCode.UNEXPECTED;

	    errorProperties.put("status", code.getStatus());
	    errorProperties.put("code", code.getCode());
	    errorProperties.put("error", "Непредвиденная ошибка.");
	    errorProperties.put("listErrors", throwable.getMessage());
	    
	    return ServerResponse
	        .status(code.getStatus())
	        .contentType(MediaType.APPLICATION_JSON)
	        .body(BodyInserters.fromValue(errorProperties));
	}
}
