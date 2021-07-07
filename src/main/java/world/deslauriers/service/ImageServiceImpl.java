package world.deslauriers.service;

import world.deslauriers.domain.Image;
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

        var a = albumService.getByAlbum(album);
        if (a.isPresent()) {
            var pics = imageRepository.findByAlbumAndPublished(a.get(), true);
            var images = new ArrayList<ImageDto>(pics.size());
            pics.forEach(pic -> images.add(loadImageDto(pic)));
            return images;
        }
        return null;
    }

    private ImageDto loadImageDto(Image pic){
        if (pic != null){
            var buffer = ByteBuffer.wrap(pic.getFilename());
            var uuid = new UUID(buffer.getLong(), buffer.getLong());
            var image = new ImageDto(
                    pic.getId(),
                    uuid.toString(),
                    pic.getTitle(),
                    pic.getDescription(),
                    pic.getDate(),
                    pic.getPublished());
            return image;
        }
        return null;
    }
}
