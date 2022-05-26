package world.deslauriers.repository;

import io.micronaut.core.io.ResourceLoader;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import world.deslauriers.domain.Image;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class ImageDaoTest {

    @Inject
    private final ImageRepository imageRepository;

    @Inject
    private final AlbumRepository albumRepository;

    @Inject
    private final ResourceLoader loader;

    public ImageDaoTest(ImageRepository imageRepository, AlbumRepository albumRepository, ResourceLoader loader) {
        this.imageRepository = imageRepository;
        this.albumRepository = albumRepository;
        this.loader = loader;
    }

    @Test
    void testImageDaoCrud() throws IOException, SQLException {

        var test_file = loader.getResourceAsStream("test_image.jpeg").get().readAllBytes();
        Blob test_image = new SerialBlob(test_file);
        var uuid = UUID.randomUUID();
        byte[] uuidBytes = new byte[16];
        ByteBuffer.wrap(uuidBytes)
                .order(ByteOrder.BIG_ENDIAN)
                .putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits());

        var pic = new Image(uuidBytes, "test", "test", LocalDate.now(), true, test_image, test_image);
        pic = imageRepository.save(pic);
        assertNotNull(pic.id());
        assertEquals(68468, pic.thumbnail().length());
        assertEquals(68468, pic.image().length());
    }

}
