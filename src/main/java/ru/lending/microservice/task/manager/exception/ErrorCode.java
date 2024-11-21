package ru.lending.microservice.task.manager.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
	FORBIDDEN(403, HttpStatus.FORBIDDEN),
	NOT_FOUND(404, HttpStatus.NOT_FOUND),
	UNEXPECTED(5000, HttpStatus.INTERNAL_SERVER_ERROR),
	VALIDATION_FAIL(4000, HttpStatus.BAD_REQUEST),
	FILE_UPLOAD_EXCEPTION(4001, HttpStatus.INTERNAL_SERVER_ERROR),
	ALREADY_EXISTS(4002, HttpStatus.CONFLICT),
	DATABASE_EXCEPTION(5003, HttpStatus.INTERNAL_SERVER_ERROR);
	
	private final Integer code;
	private final HttpStatus status;
	
	private ErrorCode(int code, HttpStatus status) {
		this.code = code;
		this.status = status;
	}
	
	public Integer getCode() {
	    return code;
	}

	public HttpStatus getStatus() {
	    return status;
	}
}
