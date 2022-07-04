package world.deslauriers.service;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

        // from test data:
        var published = "c1abcf96-9626-45aa-a606-5e3ca0c26110";
        assertTrue(imageService.getImageByFilename(published).isPresent());

        var unpublished = "b3ea8216-0f42-4777-a505-bebca3c0edfb";
        assertTrue(imageService.getImageByFilename(unpublished).isEmpty());

    }

}
