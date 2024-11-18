package ru.lending.microservice.task.manager.exception;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Error {
	public Error(String code, String message) {
		this.code = code;
		this.message = message;
	}
	private String code;
	private String message;
	private Integer status;
	private String url = "Не доступен";
	private String requestMethod = "Не доступен";
	private Instant timestamp;
}
