package ru.lending.microservice.task.manager.exception;

public class AlreadyExistsException extends CustomBaseException {
  private static final long serialVersionUID = 1L;
  private final ErrorCode errorCode;

  public AlreadyExistsException() {
    super();
    this.errorCode = ErrorCode.ALREADY_EXISTS;
  }

  public AlreadyExistsException(final String message) {
    super(message);
    this.errorCode = ErrorCode.ALREADY_EXISTS;
  }

  @Override
  public ErrorCode getErrorCode() {
    return errorCode;
  }
}