package ru.lending.microservice.task.manager;

import org.springframework.boot.SpringApplication;

public class TestTaskManagerMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.from(TaskManagerMicroserviceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
