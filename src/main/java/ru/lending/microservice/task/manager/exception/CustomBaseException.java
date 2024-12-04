package ru.lending.microservice.task.manager.exception;

public abstract class CustomBaseException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  protected CustomBaseException() {
    super();
  }

  protected CustomBaseException(String message) {
    super(message);
  }

  protected CustomBaseException(Throwable cause) {
    super(cause);
  }

  public abstract ErrorCode getErrorCode();
}
