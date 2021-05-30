package world.deslauriers.service;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

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
            assertNotNull(imageDto.getId());
            assertNotNull(imageDto.getFilename());
            assertTrue(imageDto.getPublished());

        });

        images = imageService.getImagesByAlbum(ALBUM_WRONG);
        assertNull(images);

        // all
        images = imageService.getAll();
        images.forEach(imageDto -> System.out.println(imageDto.toString()));
    }

}
