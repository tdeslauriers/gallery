package world.deslauriers.controller;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import world.deslauriers.service.dto.ImageDto;


import java.util.List;

@MicronautTest
public class ImageControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    private static final String GATEWAY_LOGIN_URI = "http://localhost:8080/login";
    private static final String VALID_USERNAME = "darth.vader@empire.com";
    private static final String VALID_PASSWORD = "#1-Pod-Racer!";

    @Test
    void testImageCrud(){}


}
