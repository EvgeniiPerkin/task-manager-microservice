package ru.lending.microservice.task.manager.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Theme;
import ru.lending.microservice.task.manager.servce.ThemeService;

@WebFluxTest(ThemeController.class)
//@Import(ThemeService.class)
class ThemeControllerTest {
  @MockBean
  ThemeService repository;

  @Autowired
  private WebTestClient client;

  @Test
  void testCreate() {
    Mono<Theme> t = Mono.just(Theme.builder()
      .id(1L)
      .departmentId(4L)
      .description("Индикатив")
      .build());

    Mockito.when(repository.create(t)).thenReturn(t);

    client.post()
    .uri("/api/v1/themes")
    .body(t, Theme.class)
    .exchange()
    .expectStatus().isCreated();
  }

  // @TestА
  // void testUpdate() {
  // 	fail("Not yet implemented");
  // }

  // @Test
  // void testDeleteById() {
  // 	fail("Not yet implemented");
  // }

}
