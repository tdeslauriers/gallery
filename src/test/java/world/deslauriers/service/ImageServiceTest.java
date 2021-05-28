package world.deslauriers.service;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
public class ImageServiceTest {

    @Inject
    private final ImageService imageService;

    public ImageServiceTest(ImageService imageService) {
        this.imageService = imageService;
    }

    // test database values
    private static final String ALBUM_2021 = "2021";
    private static final String ALBUM_WRONG = "WRONG";

    @Test
    void testImageServiceCrud(){
        //true
        var images = imageService.getImagesByAlbum(ALBUM_2021);
        images.forEach(imageDto -> System.out.println(imageDto.toString()));
    }

}
