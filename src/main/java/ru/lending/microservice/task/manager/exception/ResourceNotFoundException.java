package ru.lending.microservice.task.manager.exception;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final String key;
	private final String code;
	
	public ResourceNotFoundException(ErrorCode code) {
	    super(code.getKey());
	    this.key = code.getKey();
	    this.code = code.getCode();
	}
	
	public ResourceNotFoundException(final String message) {
	    super(message);
	    this.key = ErrorCode.RESOURCE_NOT_FOUND.getKey();
	    this.code = ErrorCode.RESOURCE_NOT_FOUND.getCode();
	}
	
	public String getKey() {
	    return key;
	}
	
	public String getCode() {
	    return code;
	}
}
