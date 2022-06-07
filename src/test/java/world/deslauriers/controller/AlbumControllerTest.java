package world.deslauriers.controller;

import io.micronaut.context.annotation.Property;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import world.deslauriers.domain.Album;
import world.deslauriers.service.dto.AlbumDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


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
        assertEquals(3, albums.spliterator().getExactSizeIfKnown());

        req = HttpRequest.GET("/albums/2018").header("Authorization", "Bearer " + token);
        var album = client
                .toBlocking()
                .retrieve(req, AlbumDto.class);
        assertNotNull(album);
        assertEquals("2018", album.album());
        assertEquals(1, album.images().size());
    }
}
