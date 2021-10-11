package com.dunwoody.farm.core.owner;

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
class OwnerServiceApplicationTests {

	private static final int BARN_ID_OK =1;
	private static final int BARN_ID_NOT_FOUND =213;
	private static final int BARN_ID_INVALID_NEGATIVE =-1;
	private static final int BARN_ID_MOVED_OR_PASSED =4000;
	private static final String BARN_ID_INVALID_STRING ="not-integer";



	@Autowired
	private WebTestClient client;

	@Test
	public void getOwnersByProductId(){
		int expectedLength =3; //length of said recommendation array/list
		client.get() //client send requests like postman
				.uri("/owner?barnId=" +BARN_ID_OK)
				.accept(MediaType.APPLICATION_JSON)
				.exchange() //get message send response
				//double check is bad request() vs is ok()
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
	public void getOwnersMissingParameter(){
		client.get()
				.uri("/owner")
				.accept(MediaType.APPLICATION_JSON)
				.exchange() //get message send response
				.expectStatus().isBadRequest()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)//what status to expecrt
				.expectBody()
				.jsonPath("$.path").isEqualTo("/owner")
				.jsonPath("$.message").isEqualTo("Required int parameter 'barnId' is not present");
	}

	@Test
	public void getOwnersInvalidParameterString(){
		client.get()
				.uri("/owner?barnId=" + BARN_ID_INVALID_STRING)
				.accept(MediaType.APPLICATION_JSON)
				.exchange() //get message send response
				.expectStatus().isBadRequest()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)//what status to expecrt
				.expectBody()
				.jsonPath("$.path").isEqualTo("/owner")
				.jsonPath("$.message").isEqualTo("Type mismatch.");
	}

	@Test
	public void getOwnersInvalidParameterNegativeValue(){
		client.get()
				.uri("/owner?barnId=" + BARN_ID_INVALID_NEGATIVE)
				.accept(MediaType.APPLICATION_JSON)
				.exchange() //get message send response
				.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
				.expectHeader().contentType(MediaType.APPLICATION_JSON)//what status to expect
				.expectBody()
				.jsonPath("$.path").isEqualTo("/owner")
				.jsonPath("$.message").isEqualTo("Invalid barnId: " + BARN_ID_INVALID_NEGATIVE);
	}

	@Test
	public void getOwnersNotFound(){
		int expectedLength=0;
		client.get()
				.uri("/owner?barnId=" + BARN_ID_NOT_FOUND)
				.accept(MediaType.APPLICATION_JSON)
				.exchange() //get message send response
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)//what status to expect
				.expectBody()
				.jsonPath("$.length()").isEqualTo(expectedLength);
	}

	@Test
	void contextLoads() {
	}

}
