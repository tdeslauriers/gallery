package world.deslauriers.repository;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import world.deslauriers.domain.Album;
import world.deslauriers.domain.Image;

import javax.inject.Inject;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class ImageRepositoryTest {

    @Inject
    private final ImageRepository imageRepository;

    @Inject
    private final AlbumRepository albumRepository;

    public ImageRepositoryTest(ImageRepository imageRepository, AlbumRepository albumRepository) {
        this.imageRepository = imageRepository;
        this.albumRepository = albumRepository;
    }

    @Test
    void testImageDaoCrud(){

        var art = new Album("Artwork");
        art = albumRepository.save(art);

        var uuid = UUID.randomUUID();
        byte[] uuidBytes = new byte[16];
        ByteBuffer.wrap(uuidBytes)
                .order(ByteOrder.BIG_ENDIAN)
                .putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits());

        var pic = new Image(uuidBytes, "test title", "test description", LocalDate.now(), false, art);
        pic = imageRepository.save(pic);
        assertNotNull(pic.getId());
        assertEquals("test title", pic.getTitle());
        assertEquals("test description", pic.getDescription());
        System.out.println("UUID generated: " + pic.getFilename().toString());
        assertEquals("Artwork", pic.getAlbum().getAlbum());

        // convert binary back to uuid
        var buf = ByteBuffer.wrap(pic.getFilename());
        var uuidName = new UUID(buf.getLong(), buf.getLong());
        System.out.println("uuid generated: " + uuid);
        System.out.println("pic uuid returned: " + uuidName);
        assertEquals(uuid, uuidName);

        var pics = imageRepository.findAll();
        pics.forEach(image -> System.out.println(image.toString()));
    }
}
