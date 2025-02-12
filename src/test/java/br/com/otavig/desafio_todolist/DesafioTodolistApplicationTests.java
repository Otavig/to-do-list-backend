package br.com.otavig.desafio_todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.otavig.desafio_todolist.entity.Todo;

@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
class DesafioTodolistApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTodoSuccess() {
		var todo = new Todo("todo1", "desc todo 1", false, 1);
		
		webTestClient
			.post()
			.uri("/todos")
			.bodyValue(todo)
			.exchange()
			.expectStatus().isOk()
			.expectBody() // Espera o corpo da resposta
			.jsonPath("$").isArray() // Garante que é um array
			.jsonPath("$.length()").isEqualTo(1) // Garante que a lista tenha apenas 1 item
			.jsonPath("$[0].nome").isEqualTo(todo.getNome()) // Verifica o nome
			.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao()) // Verifica a descrição
			.jsonPath("$[0].realizado").isEqualTo(todo.getRealizado()) // Verifica o status de realizado
			.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade()); // Verifica a prioridade
	}

	@Test
	void testCreateTodoFailure() {
		// Exemplo de teste para falha. Você pode simular uma falha, por exemplo, enviando dados inválidos.
		var todo = new Todo("", "", false, -1); // Exemplo de dados inválidos

		webTestClient
			.post()
			.uri("/todos")
			.bodyValue(todo).exchange()
			.expectStatus().isBadRequest();
	}
}
