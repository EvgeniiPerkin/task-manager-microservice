package ru.lending.microservice.task.manager.exception;

public class DatabaseException extends CustomBaseException {
	private static final long serialVersionUID = 1L;
	private final ErrorCode errorCode;

	public DatabaseException() {
		super();
	    this.errorCode = ErrorCode.DATABASE_EXCEPTION;
	}
	
	public DatabaseException(Throwable cause) {
		super(cause);
	    this.errorCode = ErrorCode.DATABASE_EXCEPTION;
	}
	
	@Override
	public ErrorCode getErrorCode() {
		return errorCode;
	}

}
