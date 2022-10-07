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

    private static final String ALBUM_2021 = "2021";
    private static final String ALBUM_WRONG = "WRONG";

    @Test
    void testAlbumServiceCrud(){

        // must return optional empty if album does not exist
        var album = albumService.getThumbnailsByAlbum(ALBUM_WRONG);
        assertTrue(album.isEmpty());

        // values in test database
        // must return images from album
        album = albumService.getThumbnailsByAlbum(ALBUM_2021);
        assertTrue(album.isPresent());
        assertTrue(album.get().thumbnails().size() > 0);
        album.get().thumbnails().forEach(thumbnailDto -> {
            assertNotNull(thumbnailDto.id());
            assertNotNull(thumbnailDto.date());
            assertNotNull(thumbnailDto.published());
            assertNotNull(thumbnailDto.thumbnail());
        });

        albumService.getAll().forEach(album1 -> System.out.println(album1.album()));
    }
}
