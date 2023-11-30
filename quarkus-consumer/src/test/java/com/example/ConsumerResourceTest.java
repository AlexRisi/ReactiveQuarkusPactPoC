package com.example;

import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static io.restassured.RestAssured.given;

@QuarkusTest
@ExtendWith(PactConsumerTestExt.class)
//@PactBroker(host = "192.168.1.35", port = "9292")
//@PactBroker(url = "http://192.168.35.1:9292")
//@Provider("quarkus-consumer")
public class ConsumerResourceTest {

    /*@Inject
    @RestClient
    ContactService contactServiceMock;*/

    /*@Inject
    @RestClient
    ContactService contactService;*/

    /*@Test
    public void testHelloEndpoint() {
        given()
          .when().get("/test")
          .then()
             .statusCode(200)
             .body(is("Hello!"));
    }*/

    @Pact(provider = "quarkus-provider", consumer = "quarkus-consumer")
    public V4Pact contactPact(PactDslWithProvider builder) {
        var body = new PactDslJsonBody()
                .stringType("name")
                .stringType("address")
                .stringType("phone");
        return builder
                .given("CreateContact")
                .uponReceiving("a request to create a contact")
                .path("/test")
                .method("POST")
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .body(body)
                .toPact(V4Pact.class);
    }

    /*@Test
    @PactTestFor(providerName = "quarkus-provider", port = "8080", pactVersion = PactSpecVersion.V4)
    public void createContactTest() {


        var contactClient = contactServiceMock.createContactAsync(contact)
                .await().indefinitely();

        assertNotNull(contactClient);
        assertNotNull(contactClient.name());
        assertNotNull(contactClient.address());
        assertNotNull(contactClient.phone());
    }*/

    @Test
    @PactTestFor(providerName = "quarkus-provider", port = "8080")
    public void createContactTest() {

        given()
                .when()
                .get("/test/async")
                .then()
                .statusCode(200);
    }
}