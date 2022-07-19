package world.deslauriers.service;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import world.deslauriers.domain.Image;
import world.deslauriers.service.dto.ImageUpdateDto;

import java.sql.SQLException;
import java.util.Arrays;

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
    void testImageServiceCrud() throws SQLException {

        // from test data:
        var published = imageService.getImageByFilename("b33176d5-6eb8-484f-a2e9-cb5204424fcd");
        assertTrue(published.isPresent());

        var unpublished = imageService.getImageByFilename("b3ea8216-0f42-4777-a505-bebca3c0edfb");
        assertTrue(unpublished.isEmpty());


        var updated = imageService.updateImage(new ImageUpdateDto(
                                                                published.get().id(),
                                                                "updated title",
                                                                "updated description",
                                                                published.get().published()));
        assertNotNull(updated.id());
        assertEquals(published.get().id(), updated.id());
        assertEquals("updated title", updated.title());

        // all unpublished
        var allUnpublished = imageService.getAllUnpublished();
        assertTrue(allUnpublished.spliterator().getExactSizeIfKnown() > 0);
    }

}
