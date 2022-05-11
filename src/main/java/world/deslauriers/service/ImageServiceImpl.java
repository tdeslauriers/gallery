package world.deslauriers.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import world.deslauriers.domain.Image;
import world.deslauriers.repository.ImageRepository;
import world.deslauriers.service.dto.ImageDto;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Singleton
public class ImageServiceImpl implements ImageService {

    @Inject
    private final ImageRepository imageRepository;

    @Inject
    private final AlbumService albumService;

    public ImageServiceImpl(ImageRepository imageRepository, AlbumService albumService) {
        this.imageRepository = imageRepository;
        this.albumService = albumService;
    }


    @Override
    public List<ImageDto> getAllPublished(){

        var pics = imageRepository.findByPublished(true);
        var images = new ArrayList<ImageDto>(pics.size());
        pics.forEach(pic -> images.add(loadImageDto(pic)));

        return images;
    }

    @Override
    public List<ImageDto> getImagesByAlbum(String album){


        return null;
    }

    private ImageDto loadImageDto(Image pic){
        if (pic != null){
            var buffer = ByteBuffer.wrap(pic.filename());
            var uuid = new UUID(buffer.getLong(), buffer.getLong());
            var image = new ImageDto(
                    pic.id(),
                    uuid.toString(),
                    pic.title(),
                    pic.description(),
                    pic.date(),
                    pic.published());
            return image;
        }
        return null;
    }
}
