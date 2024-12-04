package ru.lending.microservice.task.manager.exception;

public class NotFoundException extends CustomBaseException {
  private static final long serialVersionUID = 1L;
  private final ErrorCode errorCode;

  public NotFoundException() {
    super();
    this.errorCode = ErrorCode.NOT_FOUND;
  }

  public NotFoundException(final String message) {
    super(message);
    this.errorCode = ErrorCode.NOT_FOUND;
  }

  @Override
  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
