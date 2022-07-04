package world.deslauriers.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import world.deslauriers.domain.Image;
import world.deslauriers.repository.ImageRepository;

import java.util.Optional;

@Singleton
public class ImageServiceImpl implements ImageService {

    @Inject
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Optional<Image> getImageByFilename(String filename){
        return imageRepository.findByFilenameAndPublishedTrue(filename);
    }
}
