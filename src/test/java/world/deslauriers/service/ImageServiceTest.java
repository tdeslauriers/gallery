package world.deslauriers.service;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import world.deslauriers.service.dto.ImageUpdateDto;

import java.sql.SQLException;

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
        var published = imageService
                .getImageByUuid("b33176d5-6eb8-484f-a2e9-cb5204424fcd")
                .block();
        assertNotNull(published);

        var unpublished = imageService
                .getImageByUuid("b3ea8216-0f42-4777-a505-bebca3c0edfb")
                .block();
        assertNull(unpublished);


        var updated = imageService
                .updateImage(new ImageUpdateDto(
                                published.getId(),
                                "updated title",
                                "updated description",
                                published.getPublished())
                )
                .block();
        assertNotNull(updated.getId());
        assertEquals(published.getId(), updated.getId());
        assertEquals("updated title", updated.getTitle());

        // all unpublished
        var allUnpublished = imageService
                .getAllUnpublished()
                .collectList()
                .block();
        assertTrue(allUnpublished.size() > 0);

        imageService
                .deleteImage(published.getFilename())
                .block();
        var deleted = imageService
                .getImageByUuid(published.getFilename())
                .block();
        assertNull(deleted);
    }

}
