package world.deslauriers.service;

import io.micronaut.context.annotation.Value;
import world.deslauriers.repository.ImageRepository;
import world.deslauriers.service.dto.ImageDto;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Singleton
public class ImageServiceImpl implements ImageService {

    @Value("${album.default.size}")
    private Integer albumSize;

    @Inject
    private final ImageRepository imageRepository;

    @Inject
    private final AlbumService albumService;

    public ImageServiceImpl(ImageRepository imageRepository, AlbumService albumService) {
        this.imageRepository = imageRepository;
        this.albumService = albumService;
    }

    @Override
    public List<ImageDto> getImagesByAlbum(String album){

        var images = new ArrayList<ImageDto>(albumSize);
        var a = albumService.getByAlbum(album);
        if (a.isPresent()) {
            var pics = imageRepository.findByAlbumAndPublished(a.get(), true);
            pics.forEach(pic -> {
                var buffer = ByteBuffer.wrap(pic.getFilename());
                var uuid = new UUID(buffer.getLong(), buffer.getLong());
                var image = new ImageDto(
                        pic.getId(),
                        uuid.toString(),
                        pic.getTitle(),
                        pic.getDescription(),
                        pic.getDate(),
                        pic.getPublished());
                images.add(image);
            });
            return images;
        }
        return null;
    }
}
