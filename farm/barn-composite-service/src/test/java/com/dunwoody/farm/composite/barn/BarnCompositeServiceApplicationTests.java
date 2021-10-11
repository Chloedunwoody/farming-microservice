package com.dunwoody.farm.composite.barn;



import com.dunwoody.api.core.barn.Barn;
import com.dunwoody.api.core.horse.Horse;
import com.dunwoody.api.core.owner.Owner;
import com.dunwoody.farm.composite.barn.getBarn.integrationlayer.BarnCompositeIntegration;
import com.dunwoody.utils.exceptions.InvalidInputException;
import com.dunwoody.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = RANDOM_PORT)
class BarnCompositeServiceApplicationTests {

	private static final int BARN_ID_OK =1;
	private static final int BARN_ID_NOT_FOUND =213;
	private static final int BARN_ID_INVALID_NEGATIVE =-1;
	private static final int BARN_ID_MOVED_OR_PASSED =1000;
	private static final String BARN_ID_INVALID_STRING ="not-integer";


	@Autowired
	private WebTestClient client;

	@MockBean
	private BarnCompositeIntegration compositeIntegration;

	@BeforeEach
	void setup(){
		when(compositeIntegration.getBarn(BARN_ID_OK))
				.thenReturn(new Barn(BARN_ID_OK, "Shadow River", "2345 River Rd.", 30,"mock address"));
	//Bdd
		when(compositeIntegration.getHorses(BARN_ID_OK))
				.thenReturn(singletonList(new Horse(BARN_ID_OK, 1, "Henry",3,"gelding", "Hackney",false, false, false,"mock address")));

		when(compositeIntegration.getOwners(BARN_ID_OK))
				.thenReturn(singletonList(new Owner(BARN_ID_OK, 213, "Chelsey", 23, 24.60,"mock address")));

		when(compositeIntegration.getBarn(BARN_ID_NOT_FOUND))
				.thenThrow(new NotFoundException("NOT FOUND: " + BARN_ID_NOT_FOUND));

		when(compositeIntegration.getBarn(BARN_ID_INVALID_NEGATIVE))
				.thenThrow(new InvalidInputException("INVALID: " + BARN_ID_INVALID_NEGATIVE));

		when(compositeIntegration.getBarn(BARN_ID_MOVED_OR_PASSED))
				.thenThrow(new NotFoundException("Barn has been moved or passed for barnId: " + BARN_ID_MOVED_OR_PASSED));

	}
	@Test
	public void getBarnById() {
		int expectedLength = 1;
		client.get()
				.uri("/barn-composite/" + BARN_ID_OK)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.barnId").isEqualTo(BARN_ID_OK)
				.jsonPath("$.horses.length()").isEqualTo(expectedLength)
				.jsonPath("$.owners.length()").isEqualTo(expectedLength);
	}
	@Test
	public void getBarnNotFound() {
		client.get()
				.uri("/barn-composite/" + BARN_ID_NOT_FOUND)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNotFound()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/barn-composite/" +BARN_ID_NOT_FOUND)
				.jsonPath("$.message").isEqualTo("NOT FOUND: " + BARN_ID_NOT_FOUND);
	}
	@Test
	public  void getBarnInvalidParameterNegativeValue() {
		client.get()
				.uri("/barn-composite/" + BARN_ID_INVALID_NEGATIVE)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/barn-composite/" +BARN_ID_INVALID_NEGATIVE)
				.jsonPath("$.message").isEqualTo("INVALID: " + BARN_ID_INVALID_NEGATIVE);
	}


	@Test
	public  void getBarnInvalidParameterStringValue() {
		client.get()
				.uri("/barn-composite/" + BARN_ID_INVALID_STRING)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.path").isEqualTo("/barn-composite/" + BARN_ID_INVALID_STRING)
				.jsonPath("$.message").isEqualTo("Type mismatch.");
	}

//	@Test
//	public void getBarnPassedOrMoved() {
//		client.get()
//				.uri("barn-composite/" + BARN_ID_MOVED_OR_PASSED)
//				.accept(MediaType.APPLICATION_JSON)
//				.exchange()
//				.expectStatus().isEqualTo(HttpStatus.GONE)
//				.expectHeader().contentType(MediaType.APPLICATION_JSON)
//				.expectBody()
//				.jsonPath("$.path").isEqualTo("/barn-composite/" + BARN_ID_MOVED_OR_PASSED)
//				.jsonPath("$.message").isEqualTo("Barn has been moved or passed for barnId: " + BARN_ID_MOVED_OR_PASSED);
//	}

	@Test
	void contextLoads() {
	}

}
