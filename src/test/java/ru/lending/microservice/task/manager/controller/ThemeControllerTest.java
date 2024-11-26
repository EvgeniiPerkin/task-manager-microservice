package ru.lending.microservice.task.manager.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.lending.microservice.task.manager.entity.Theme;
import ru.lending.microservice.task.manager.entity.dto.ThemeDto;
import ru.lending.microservice.task.manager.service.impl.ThemeServiceImpl;

@RunWith(SpringRunner.class)
@WebFluxTest(ThemeController.class)
class ThemeControllerTest {
	@MockBean
	ThemeServiceImpl service;
	  
	@Autowired
	private WebTestClient client;

	@Test
	void testCreate() {
		Mono<ThemeDto> monoThemeDto = Mono.just(new ThemeDto(1L, "Индикатив"));

		Mono<Theme> monoTheme = Mono.just(Theme.builder().id(1L).departmentId(1L).description("Индикатив").build());

		when(service.create(monoThemeDto)).thenReturn(monoTheme);
		client.post()
			.uri("/api/v1/themes")
			.body(BodyInserters.fromValue(new ThemeDto(1L, "Индикатив")))
			.exchange()
			.expectStatus().isCreated()
			.expectBody().isEmpty();
		/*client.post()
			.uri("/api/v1/themes")
			.body(Mono.just(monoThemeDto), ThemeDto.class)
			.exchange()
			.expectStatus().isOk();*/
	}


	@Test
	void testFindByDepartmentId() {
 		Theme t1 = new Theme();
    t1.setId(1L);
    t1.setDepartmentId(1L);
    t1.setDescription("Индикатив.");

    Theme t3 = new Theme();
    t3.setId(3L);
    t3.setDepartmentId(1L);
    t3.setDescription("Предварительный СБ");
                 
    when(service.findByDepartmentId(1L)).thenReturn(Flux.just(t1, t3));
        
    client.get().uri("/api/v1/themes/{id}", 1L)
    	.header(HttpHeaders.ACCEPT, "application/json")
      .exchange()
      .expectStatus().isOk()
      .expectBodyList(Theme.class);
        
    verify(service, Mockito.times(1)).findByDepartmentId(1L);
 }

	// @Test
	// void testUpdate() {
	// 	fail("Not yet implemented");
	// }

	// @Test
	// void testDeleteById() {
	// 	fail("Not yet implemented");
	// }

}
