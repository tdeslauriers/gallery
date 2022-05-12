package world.deslauriers.service;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class AlbumServiceTest {

    @Inject
    private final AlbumService albumService;

    public AlbumServiceTest(AlbumService albumService) {
        this.albumService = albumService;
    }

    private static final String ALBUM_2021 = "2018";
    private static final String ALBUM_ART = "Art";
    private static final String ALBUM_WRONG = "WRONG";

    @Test
    void testAlbumServiceCrud(){

        // must return optional empty if album does not exist
        var album = albumService.getByAlbum(ALBUM_WRONG);
        assertTrue(album.isEmpty());

        // values in test database
        // must return images from album
        album = albumService.getByAlbum(ALBUM_2021);
        assertTrue(album.isPresent());
        assertEquals(1, album.get().images().size());

        var pic2018 = album.get().images().stream().iterator().next();

        album = albumService.getByAlbum(ALBUM_ART);
        assertTrue(album.isPresent());
        assertEquals(1, album.get().images().size());

        var picArt = album.get().images().stream().iterator().next();

        // image must appear in each assigned album
        assertEquals(pic2018, picArt);
    }
}
