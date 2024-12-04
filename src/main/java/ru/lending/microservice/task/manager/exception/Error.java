package ru.lending.microservice.task.manager.exception;

import java.time.Instant;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
  description = "Структура данных ошибки.", 
  title = "Error", 
  example = """
    {
    	"timestamp": "2024-11-21T08:00:12.700+00:00",
    	"path": "/api/v1/..",
        "status": "BAD_REQUEST",
        "error": "Ошибка валидации данных.",
        "requestId": "6005f20b-1",
        "code": 4000,
        "listErrors":[
    	    "Строка описания не может быть пустой.",
    	    "Идентификатор не может быть равен null."
    	]
    }"""
  )
public class Error {
  @Schema(description = "Дата и время.", example = "2024-11-21T08:00:12.700+00:00")
  private Instant timestamp;
  @Schema(description = "Путь.", example = "/api/v1/")
  private String path;
  @Schema(description = "Статус.", example = "BAD_REQUEST")
  private String status;
  @Schema(description = "Заголовок.", example = "Ошибка валидации данных.")
  private String error;
  @Schema(description = "Идентификатор запроса.", example = "6005f20b-1")
  private String requestId;
  @Schema(description = "Код.", example = "4000")
  private Integer code;
  @Schema(description = "Список ошибок.", example ="""
    [
        "Строка описания не может быть пустой.",
        "Идентификатор не может быть равен null."
    ]
    """)
  private List<String> listErrors;
}
