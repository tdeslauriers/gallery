package world.deslauriers.service;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class AlbumServiceTest {

    @Inject
    private final AlbumService albumService;

    public AlbumServiceTest(AlbumService albumService) {
        this.albumService = albumService;
    }

    private static final String ALBUM_2021 = "2021";
    private static final String ALBUM_WRONG = "WRONG";

    @Test
    void testAlbumServiceCrud(){

        // values in test database
        var album = albumService.getByAlbum(ALBUM_2021);
        System.out.println("Album by String: " + album.get());
        assertNotNull(album);
        assertEquals(ALBUM_2021, album.get().getAlbum());
        assertNull(album.get().getImages()); // not needed for this service call.  no join.

        album = albumService.getByAlbum(ALBUM_WRONG);
        System.out.println("Incorrect album name given: " + album.toString());
        assertTrue(album.isEmpty());
    }
}
