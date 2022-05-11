package world.deslauriers.repository;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import world.deslauriers.domain.Image;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public class ImageDaoTest {

    @Inject
    private final ImageRepository imageRepository;

    @Inject
    private final AlbumRepository albumRepository;

    public ImageDaoTest(ImageRepository imageRepository, AlbumRepository albumRepository) {
        this.imageRepository = imageRepository;
        this.albumRepository = albumRepository;
    }

    @Test
    void testImageDaoCrud(){

        var uuid = UUID.randomUUID();
        byte[] uuidBytes = new byte[16];
        ByteBuffer.wrap(uuidBytes)
                .order(ByteOrder.BIG_ENDIAN)
                .putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits());

        var pic = new Image(uuidBytes, "test title", "test description", LocalDate.now(), false, null);
        pic = imageRepository.save(pic);
        assertNotNull(pic.id());
        assertEquals("test title", pic.title());
        assertEquals("test description", pic.description());
        System.out.println("UUID generated: " + Arrays.toString(pic.filename()));

        // convert binary back to uuid
        var buf = ByteBuffer.wrap(pic.filename());
        var uuidName = new UUID(buf.getLong(), buf.getLong());
        System.out.println("uuid generated: " + uuid);
        System.out.println("pic uuid returned: " + uuidName);
        assertEquals(uuid, uuidName);

        var pics = imageRepository.findByPublished(false);
        pics.forEach(image -> System.out.println(image.toString()));
    }

}
