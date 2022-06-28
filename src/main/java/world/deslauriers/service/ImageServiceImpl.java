package world.deslauriers.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import world.deslauriers.domain.Image;
import world.deslauriers.repository.ImageRepository;
import world.deslauriers.service.dto.ThumbnailDto;

@Singleton
public class ImageServiceImpl implements ImageService {

    @Inject
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public ThumbnailDto loadThumbnailDto(Image pic){

            return new ThumbnailDto(
                    pic.id(),
                    pic.filename(),
                    pic.title(),
                    pic.description(),
                    pic.date(),
                    pic.published(),
                    pic.thumbnail());
    }
}
