package world.deslauriers.service;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

@MicronautTest
public class AlbumServiceTest {

    private final AlbumService albumService;

    public AlbumServiceTest(AlbumService albumService) {
        this.albumService = albumService;
    }

    private static final String ALBUM_2021 = "2021";
    private static final String ALBUM_WRONG = "WRONG";

    @Test
    void testAlbumServiceCrud(){

    }
}
