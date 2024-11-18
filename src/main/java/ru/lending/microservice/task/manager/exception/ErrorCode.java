package ru.lending.microservice.task.manager.exception;

public enum ErrorCode {
	GENERIC_ERROR("E0001", "Системе не удается выполнить запрос. Обратитесь в службу поддержки системы."),
	HTTP_MEDIATYPE_NOT_SUPPORTED("E0002", "Запрашиваемый медиа-тип не поддерживается. Пожалуйста, используйте application/json в качестве значения заголовка Content-Type"),
	HTTP_MESSAGE_NOT_WRITABLE("E0003", "Отсутствует заголовок 'Accept'. Пожалуйста, добавьте заголовок 'Accept'."),
	HTTP_MEDIA_TYPE_NOT_ACCEPTABLE("E0004", "Запрошенное значение заголовка 'Accept' не поддерживается. Пожалуйста, используйте application/json в качестве значения 'Accept'."),
	JSON_PARSE_ERROR("E0005", "Убедитесь, что полезная нагрузка запроса должна быть допустимым объектом JSON."),
	HTTP_MESSAGE_NOT_READABLE("E0006", "Убедитесь, что полезная нагрузка запроса должна быть допустимым объектом JSON в соответствии с 'Content-Type'."),
	HTTP_REQUEST_METHOD_NOT_SUPPORTED("E0007", "Метод запроса не поддерживается."),
	CONSTRAINT_VIOLATION("E0008", "Ошибка валидации данных."),
	ILLEGAL_ARGUMENT_EXCEPTION("E0009", "Переданы недопустимые данные."),
	RESOURCE_NOT_FOUND("E0010", "Запрошенный ресурс не найден."),
	GENERIC_ALREADY_EXISTS("E0011", "Уже существует.");
	
	private String code;
	private String key;
	
	private ErrorCode(final String code, final String key) {
		this.code = code;
		this.key = key;
	}
	
	public String getCode() {
	    return code;
	}

	public String getKey() {
	    return key;
	}
}
