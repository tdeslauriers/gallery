package world.deslauriers.controller;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import world.deslauriers.domain.Image;

@MicronautTest
public class ImageControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Property(name = "test.bearer")
    String token;

    @Test
    void testImageCrud(){

        // test data
        var req = HttpRequest
                .GET("/images/c1abcf96-9626-45aa-a606-5e3ca0c26110")
                .header("Authorization", "Bearer " + token);
        var res = client
                .toBlocking()
                .retrieve(req, Image.class);
        Assertions.assertNotNull(res.image());

        // un-published
        var fourZeroFour = HttpRequest.GET("/images/b3ea8216-0f42-4777-a505-bebca3c0edfb").header("Authorization", "Bearer " + token);
        var thrown = Assertions.assertThrows(HttpClientResponseException.class, () -> {
            client
                    .toBlocking()
                    .retrieve(fourZeroFour, Image.class);
        });
        Assertions.assertEquals("Not Found", thrown.getMessage());
    }


}
