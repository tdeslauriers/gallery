package world.deslauriers.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import world.deslauriers.domain.Image;
import world.deslauriers.repository.ImageRepository;
import world.deslauriers.service.dto.ImageUpdateDto;

import java.sql.SQLException;
import java.util.Optional;

@Singleton
public class ImageServiceImpl implements ImageService {

    private static final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Inject
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Optional<Image> getImageByFilename(String filename){
        return imageRepository.findByFilenameAndPublishedTrue(filename);
    }

    @Override
    public Image updateImage(ImageUpdateDto img) throws SQLException {

       if (imageRepository.findById(img.id()).isEmpty()){

           log.error("Attempt to update record that does not exist.");
            throw new SQLException("Record not found.");
       }

        imageRepository.updateImage(img.id(), img.title(), img.description(), img.published());
        return imageRepository.findById(img.id()).orElse(null);
    }
}
