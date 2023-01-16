package world.deslauriers.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import world.deslauriers.domain.Image;
import world.deslauriers.repository.AlbumImageRepository;
import world.deslauriers.repository.ImageRepository;
import world.deslauriers.service.dto.FullResolutionDto;
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
    public Iterable<Long> listImageIds(){
        return imageRepository.findAllImageIds();
    }

    @Override
    public Optional<Image> getImageByFilename(String filename){
        var dto = imageRepository.findByFilename(filename);
        var image = new Image();
        if (dto.isPresent()){
            image.setId(dto.get().id());
            image.setFilename(dto.get().filename());
            image.setTitle(dto.get().title());
            image.setDescription(dto.get().description());
            image.setDate(dto.get().date());
            image.setPublished(dto.get().published());
            image.setThumbnail(dto.get().thumbnail());
            image.setPresentation(dto.get().presentation());

            var ai = albumImageRepository.findByImageId(dto.get().id());
            if (ai.spliterator().getExactSizeIfKnown() > 0){
                image.setAlbumImages(ai);
            }
        }

        return Optional.of(image);
    }

    @Override
    public void updateImage(ImageUpdateDto img) throws SQLException {

       if (imageRepository.findById(img.id()).isEmpty()){

           log.error("Attempt to update record that does not exist.");
            throw new SQLException("Record not found.");
       }
       imageRepository.updateImage(img.id(), img.title(), img.description(), img.published());
    }

    @Override
    public Optional<FullResolutionDto> getFullResolution(String filename){
        return imageRepository.findFullResolutionByFilename(filename);
    }

    @Override
    public Optional<Image> getImageById(Long id) {
        return imageRepository.findById(id);
    }

//    @Override
//    public void deleteImage(String filename) throws SQLException{
//
//        var deleted = imageRepository.findByFilename(filename);
//        if (deleted.isEmpty()){
//            log.error("Attempt to delete a record that does not exist.");
//            throw new SQLException("Record not found");
//        }
//
//        // remove xrefs before deletion
//        deleted.get().albumImages().forEach(albumImageRepository::delete);
//        imageRepository.delete(deleted.get());
//    }
}
