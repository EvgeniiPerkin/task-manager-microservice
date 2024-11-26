package ru.lending.microservice.task.manager.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import ru.lending.microservice.task.manager.entity.Condition;
import ru.lending.microservice.task.manager.service.impl.ConditionServiceImpl;

@WebFluxTest(controllers = ConditionController.class)
@Import(ConditionServiceImpl.class)
class ConditionControllerTest {
    @MockBean
    ConditionServiceImpl service;

    @Autowired
    private WebTestClient client;
    
	@Test
	void testGetAll() {
		Condition c1 = new Condition();
        c1.setId(1L);
        c1.setDescription("Высокий");
       
        Condition c2 = new Condition();
        c2.setId(2L);
        c2.setDescription("Средний");
        
        Mockito.when(service.getAll()).thenReturn(Flux.just(c1, c2));

        client.get()
                .uri("/api/v1/conditions")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Condition.class);

        Mockito.verify(service, Mockito.times(1)).getAll();
	}

}
