package world.deslauriers.service;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

@MicronautTest
public class ImageServiceTest {

    private final ImageService imageService;

    public ImageServiceTest(ImageService imageService) {
        this.imageService = imageService;
    }

    // test database values
    private static final String ALBUM_2021 = "2021";
    private static final String ALBUM_WRONG = "NOPE";



}
