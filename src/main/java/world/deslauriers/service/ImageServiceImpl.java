package world.deslauriers.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import world.deslauriers.domain.Image;
import world.deslauriers.repository.ImageRepository;
import world.deslauriers.service.dto.ImageDto;

import java.nio.ByteBuffer;
import java.util.UUID;

@Singleton
public class ImageServiceImpl implements ImageService {

    @Inject
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageDto loadImageDto(Image pic){
            var buffer = ByteBuffer.wrap(pic.filename());
            var uuid = new UUID(buffer.getLong(), buffer.getLong());
            return new ImageDto(
                    pic.id(),
                    uuid.toString(),
                    pic.title(),
                    pic.description(),
                    pic.date(),
                    pic.published());
    }
}
