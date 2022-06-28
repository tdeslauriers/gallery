package world.deslauriers.repository;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

@MicronautTest
public class ImageDaoTest {

    @Inject
    private final ImageRepository imageRepository;

    public ImageDaoTest(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Test
    void testImageDaoCrud() throws IOException, SQLException {

        var pics = imageRepository.findAll();
        pics.forEach(image -> System.out.println(image));
    }

}
