package ru.lending.microservice.task.manager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {
  @Bean
  OpenAPI productServiceAPI() {
    return new OpenAPI()
      .info(new Info().title("Task Manager API.")
        .description("REST API для сервиса задач.")
        .version("v0.0.1")
        .license(new License().name("Apache 2.0")))
      .externalDocs(new ExternalDocumentation()
        .description("Вы можете подробно ознакомитсья с документацией по этой ссылке.")
        .url("https://perkin-developer.ru/docs"));
  }
}
