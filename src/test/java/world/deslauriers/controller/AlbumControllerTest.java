package world.deslauriers.controller;

import io.micronaut.context.annotation.Property;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import world.deslauriers.domain.Album;
import world.deslauriers.service.dto.ThumbnailDto;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@MicronautTest
public class AlbumControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Property(name = "test.bearer")
    String token;

    @Test
    void testAlbumRest(){

        var req = HttpRequest.GET("/albums").header("Authorization", "Bearer " + token);
        var albums = client
                .toBlocking()
                .retrieve(req, Argument.of(Iterable.class, Album.class));
        assertNotNull(albums);
        assertTrue(albums.spliterator().getExactSizeIfKnown() > 0);
        albums.forEach(o -> System.out.println(o));

        req = HttpRequest.GET("/albums/2021").header("Authorization", "Bearer " + token);
        var album = client
                .toBlocking()
                .retrieve(req, Argument.of(Flux.class, ThumbnailDto.class));


    }
}
