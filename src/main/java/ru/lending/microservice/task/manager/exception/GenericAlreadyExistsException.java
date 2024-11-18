package ru.lending.microservice.task.manager.exception;

public class GenericAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final String key;
	private final String code;
	
	public GenericAlreadyExistsException(ErrorCode code) {
	    super(code.getKey());
	    this.key = code.getKey();
	    this.code = code.getCode();
	}
	
	public GenericAlreadyExistsException(final String message) {
	    super(message);
	    this.key = ErrorCode.GENERIC_ALREADY_EXISTS.getKey();
	    this.code = ErrorCode.GENERIC_ALREADY_EXISTS.getCode();
	}
	
	public String getKey() {
	    return key;
	}
	
	public String getCode() {
	    return code;
	}
}