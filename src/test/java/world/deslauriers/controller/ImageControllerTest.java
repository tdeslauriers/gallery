package world.deslauriers.controller;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
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

    @Test
    void testImageCrud(){

        var request = HttpRequest.GET("/images");
        List<ImageDto> images = client
                .toBlocking()
                .retrieve(request, Argument.of(List.class, ImageDto.class));
        Assertions.assertNotNull(images);
        images.forEach(imageDto -> {
            Assertions.assertTrue(imageDto.getPublished());
            System.out.println("Get All Images: " + imageDto.toString());
        });

        request = HttpRequest.GET("/images/2021");
        images = client
                .toBlocking()
                .retrieve(request, Argument.of(List.class, ImageDto.class));
        images.forEach(imageDto -> {
            System.out.println("Get By Album: " + imageDto.toString());
            Assertions.assertEquals(2021, imageDto.getDate().getYear());
            Assertions.assertTrue(imageDto.getPublished());
        });
    }
}
