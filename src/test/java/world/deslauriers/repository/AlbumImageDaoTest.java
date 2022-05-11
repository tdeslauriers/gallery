package world.deslauriers.repository;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;


@MicronautTest
public class AlbumImageDaoTest {

    @Inject
    private AlbumImageRepository albumImageRepository;

    @Test
    void testAlbumImageCRUD(){

        var all = albumImageRepository.findAll();
        all.forEach(System.out::println);
    }
}
