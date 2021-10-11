package com.dunwoody.farm.core.barn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = RANDOM_PORT)
class BarnServiceApplicationTests {

	private static final int BARN_ID_OK =1;
	private static final int BARN_ID_NOT_FOUND =13;
	private static final int BARN_ID_INVALID_NEGATIVE =-1;
	private static final int BARN_ID_MOVED_OR_PASSED =2000;
	private static final String BARN_ID_INVALID_STRING ="not-integer";

	@Autowired
	private WebTestClient client;

	@Test
	public void getBarnById(){
		client.get()
				.uri("/barn/" +BARN_ID_OK)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.barnId").isEqualTo(BARN_ID_OK);

	}
	@Test
	public void getBarnInvalidParameterString(){
		client.get()
				.uri("/barn/" + BARN_ID_INVALID_STRING )
				//	isEqualTo(HttpStatus.BAD_REQUEST) ?? unknown
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				//double check is bad request() vs is ok()
				.expectStatus().isBadRequest()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/barn/" + BARN_ID_INVALID_STRING)
				.jsonPath("$.message").isEqualTo("Type mismatch.");
	}

	@Test
	public void getBarnNotFound(){
		client.get()
				.uri("/barn/" + BARN_ID_NOT_FOUND)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNotFound()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/barn/" + BARN_ID_NOT_FOUND)
				.jsonPath("$.message").isEqualTo("No Barn found for barnId: " + BARN_ID_NOT_FOUND);
		//must match .message from json !!!

	}

	@Test
	public void getBarnInvalidParameterNegativeValue(){
		client.get()
				.uri("/barn/" + BARN_ID_INVALID_NEGATIVE)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/barn/" + BARN_ID_INVALID_NEGATIVE)
				.jsonPath("$.message").isEqualTo("Invalid barnId: " + BARN_ID_INVALID_NEGATIVE);
		//must match .message from json !!!

	}
	@Test
	public void getBarnPassedOrMoved() {
		client.get()
				.uri("/barn/" + BARN_ID_MOVED_OR_PASSED)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isEqualTo(HttpStatus.GONE)
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/barn/" + BARN_ID_MOVED_OR_PASSED)
				.jsonPath("$.message").isEqualTo("Barn has been moved or passed for barnId: " + BARN_ID_MOVED_OR_PASSED);
	}

	@Test
	void contextLoads() {
	}

}
