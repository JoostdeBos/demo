package com.joostdebos.udemy;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import com.fasterxml.jackson.databind.JsonNode;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class HelloWorldControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void helloWorldEndpointRespondWithCorrectString() {
        var response = client.toBlocking().retrieve("hello");
        assertEquals("hello from micronaut service", response);
    }

    @Test
    void helloWorldEndpointRespondWithCorrectStatusCode() {
        var response = client.toBlocking().exchange("hello", String.class);
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @Test
    void helloFromConfigReturnsConfigString() {
        var response = client.toBlocking().exchange("hello/config", String.class);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("hello from yml", response.getBody().get());
    }

    @Test
    void helloFromTranslationReturnsTranslationString() {
        var response = client.toBlocking().exchange("hello/translation", JsonNode.class);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("{\"nl\":\"hallo wereld\",\"en\":\"hello world\"}", response.getBody().get().toString());
    }

}
