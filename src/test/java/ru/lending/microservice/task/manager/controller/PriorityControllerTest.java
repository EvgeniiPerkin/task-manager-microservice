package ru.lending.microservice.task.manager.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import ru.lending.microservice.task.manager.entity.Priority;
import ru.lending.microservice.task.manager.service.impl.PriorityServiceImpl;

@WebFluxTest(controllers = PriorityController.class)
@Import(PriorityServiceImpl.class)
class PriorityControllerTest {
  @MockBean
  PriorityServiceImpl service;

  @Autowired
  private WebTestClient client;

  @Test
  void testGetAll() {
    Priority p1 = new Priority();
    p1.setId(1L);
    p1.setDescription("Высокий");

    Priority p2 = new Priority();
    p2.setId(2L);
    p2.setDescription("Средний");

    Mockito.when(service.getAll()).thenReturn(Flux.just(p1, p2));

    client.get()
    .uri("/api/v1/priorities")
    .exchange()
    .expectStatus().isOk()
    .expectBodyList(Priority.class);

    Mockito.verify(service, Mockito.times(1)).getAll();
  }

}
