package com.projects.to_do_list;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.projects.to_do_list.entiny.Todo;
import com.projects.to_do_list.repository.TodoRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ToDoListApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private TodoRepository todoRepository;

	@BeforeEach
	void cleanDatabase() {
		todoRepository.deleteAll();
	}

	@Test
	void testCreatTodoSucess() {
		var todo = new Todo("Estudar Java", "Estudar Spring Boot", false,  1);

		webTestClient
			.post()
			.uri("/todos")
			.bodyValue(todo)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(1)
			.jsonPath("$[0].nome").isEqualTo(todo.getNome())
			.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
			.jsonPath("$[0].concluido").isEqualTo(todo.getConcluido())
			.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
	}

	@Test
	void testCreatTodoFailure() {
		webTestClient
		.post()
		.uri("/todos")
		.bodyValue(new Todo("", "", false, 1))
		.exchange()
		.expectStatus().isBadRequest();
		
	}

}
