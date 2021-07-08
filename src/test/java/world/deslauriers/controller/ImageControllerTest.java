package world.deslauriers.controller;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import world.deslauriers.service.dto.ImageDto;

import javax.inject.Inject;
import java.util.List;

@MicronautTest
public class ImageControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    private static final String GATEWAY_LOGIN_URI = "http://localhost:8080/login";
    private static final String VALID_USERNAME = "darth.vader@empire.com";
    private static final String VALID_PASSWORD = "Now-This-Is-Pod-Racing!!!";

    @Test
    void testImageCrud(){

        // security enabled, requires bearer token from gateway service
        var creds = new UsernamePasswordCredentials(VALID_USERNAME, VALID_PASSWORD);
        var loginRequest = HttpRequest.POST(GATEWAY_LOGIN_URI, creds);
        var bearer = client.toBlocking().retrieve(loginRequest, BearerAccessRefreshToken.class);




        var request = HttpRequest.GET("/images");
        List<ImageDto> images = client
                .toBlocking()
                .retrieve(request.header(
                        "Authorization", "Bearer " + bearer.getAccessToken()),
                        Argument.of(List.class, ImageDto.class));
        Assertions.assertNotNull(images);
        images.forEach(imageDto -> {
            Assertions.assertTrue(imageDto.getPublished());
            System.out.println("Get All Images: " + imageDto.toString());
        });

        request = HttpRequest.GET("/images/2021");
        images = client
                .toBlocking()
                .retrieve(request.header(
                        "Authorization", "Bearer " + bearer.getAccessToken()),
                        Argument.of(List.class, ImageDto.class));
        images.forEach(imageDto -> {
            System.out.println("Get By Album: " + imageDto.toString());
            Assertions.assertEquals(2021, imageDto.getDate().getYear());
            Assertions.assertTrue(imageDto.getPublished());
        });
    }
}
