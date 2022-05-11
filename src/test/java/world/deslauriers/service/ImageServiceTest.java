package world.deslauriers.service;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class ImageServiceTest {

    @Inject
    private final ImageService imageService;

    public ImageServiceTest(ImageService imageService) {
        this.imageService = imageService;
    }

    // test database values
    private static final String ALBUM_2021 = "2021";
    private static final String ALBUM_WRONG = "NOPE";

    @Test
    void testImageServiceCrud(){

        // by album
        var images = imageService.getImagesByAlbum(ALBUM_2021);
        images.forEach(imageDto -> {
            System.out.println(imageDto.toString());
            assertNotNull(imageDto);
            assertNotNull(imageDto.id());
            assertNotNull(imageDto.filename());
            assertTrue(imageDto.published());

        });

        images = imageService.getImagesByAlbum(ALBUM_WRONG);
        assertNull(images);

        // all
        images = imageService.getAllPublished();
        images.forEach(imageDto -> System.out.println(imageDto.toString()));
    }

}
