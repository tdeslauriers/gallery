package world.deslauriers.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import world.deslauriers.domain.Image;
import world.deslauriers.repository.AlbumImageRepository;
import world.deslauriers.repository.ImageRepository;
import world.deslauriers.service.dto.ImageUpdateDto;
import world.deslauriers.service.dto.ThumbnailDto;

import java.sql.SQLException;
import java.util.Optional;

@Singleton
public class ImageServiceImpl implements ImageService {

    private static final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Inject
    private final ImageRepository imageRepository;

    @Inject
    private final AlbumImageRepository albumImageRepository;

    public ImageServiceImpl(ImageRepository imageRepository, AlbumImageRepository albumImageRepository) {
        this.imageRepository = imageRepository;
        this.albumImageRepository = albumImageRepository;
    }

    @Override
    public Iterable<ThumbnailDto> getAllUnpublished(){
        return imageRepository.findAllUnpublished();
    }

    @Override
    public Iterable<Image> backupAll(){
        return imageRepository.findAll();
    }

    @Override
    public Optional<Image> getImageByFilename(String filename){
        return imageRepository.findByFilename(filename);
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

    @Override
    public void deleteImage(String filename) throws SQLException{

        var deleted = imageRepository.findByFilename(filename);
        if (deleted.isEmpty()){
            log.error("Attempt to delete a record that does not exist.");
            throw new SQLException("Record not found");
        }

        // remove xrefs before deletion
        deleted.get().albumImages().forEach(albumImageRepository::delete);
        imageRepository.delete(deleted.get());
    }
}
