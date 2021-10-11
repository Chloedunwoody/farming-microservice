package com.dunwoody.farm.core.horse;

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
@AutoConfigureWebTestClient //
@SpringBootTest(webEnvironment = RANDOM_PORT)
class HorseServiceApplicationTests {
	private static final int BARN_ID_OK =1;
	private static final int BARN_ID_NOT_FOUND =113;
	private static final int BARN_ID_INVALID_NEGATIVE =-1;
	private static final int BARN_ID_MOVED_OR_PASSED =3000;
	private static final String BARN_ID_INVALID_STRING ="not-integer";

	@Autowired
	private WebTestClient client;
	@Test
	public void getHorsesByBarnId(){
		int expectedLength =3; //length of said recommendation array/list
		client.get() //client send requests like postman
				.uri("/horse?barnId=" +BARN_ID_OK)
				.accept(MediaType.APPLICATION_JSON)
				.exchange() //get message send response
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)//what status to expecrt
				.expectBody()
				//path ??
				.jsonPath("$.length()").isEqualTo(expectedLength)
				.jsonPath("$[0].barnId").isEqualTo(BARN_ID_OK)
				.jsonPath("$[1].barnId").isEqualTo(BARN_ID_OK)
				.jsonPath("$[2].barnId").isEqualTo(BARN_ID_OK);

	}

	//bad request if missing thing
	//internal server means unhandled error

	@Test
	public void getHorsesMissingParameter(){
		client.get()
				.uri("/horse")
				.accept(MediaType.APPLICATION_JSON)
				.exchange() //get message send response
				.expectStatus().isBadRequest()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)//what status to expecrt
				.expectBody()
				.jsonPath("$.path").isEqualTo("/horse")
				.jsonPath("$.message").isEqualTo("Required int parameter 'barnId' is not present");
	}

	@Test
	public void getHorsesInvalidParameterString(){
		client.get()
				.uri("/horse?barnId=" + BARN_ID_INVALID_STRING)
				.accept(MediaType.APPLICATION_JSON)
				.exchange() //get message send response
				.expectStatus().isBadRequest()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)//what status to expecrt
				.expectBody()
				.jsonPath("$.path").isEqualTo("/horse")
				.jsonPath("$.message").isEqualTo("Type mismatch.");
	}

	@Test
	public void getHorsesInvalidParameterNegativeValue(){
		client.get()
				.uri("/horse?barnId=" + BARN_ID_INVALID_NEGATIVE)
				.accept(MediaType.APPLICATION_JSON)
				.exchange() //get message send response
				.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
				.expectHeader().contentType(MediaType.APPLICATION_JSON)//what status to expecrt
				.expectBody()
				.jsonPath("$.path").isEqualTo("/horse")
				.jsonPath("$.message").isEqualTo("Invalid barnId: " + BARN_ID_INVALID_NEGATIVE);
	}

	@Test
	public void getHorsesNotFound(){
		int expectedLength=0;
		client.get()
				.uri("/horse?barnId=" + BARN_ID_NOT_FOUND)
				.accept(MediaType.APPLICATION_JSON)
				.exchange() //get message send response
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)//what status to expecrt
				.expectBody()
				.jsonPath("$.length()").isEqualTo(expectedLength);
	}

	@Test
	void contextLoads() {
	}

}
