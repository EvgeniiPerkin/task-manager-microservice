package ru.lending.microservice.task.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableR2dbcRepositories
@OpenAPIDefinition(info =
@Info(title = "Task API", version = "${springdoc.version}", description = "Документация Task API v1.0")
)
public class TaskManagerMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerMicroserviceApplication.class, args);
	}
}
