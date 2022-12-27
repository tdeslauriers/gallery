package world.deslauriers.service;

import world.deslauriers.domain.Image;
import world.deslauriers.repository.dto.ImageDto;
import world.deslauriers.service.dto.ImageUpdateDto;
import world.deslauriers.service.dto.ThumbnailDto;

import java.sql.SQLException;
import java.util.Optional;

public interface ImageService {
    Iterable<ThumbnailDto> getAllUnpublished();

    Optional<Image> getImageByFilename(String filename);

    Image updateImage(ImageUpdateDto img) throws SQLException;


//    void deleteImage(String filename) throws SQLException;

    Iterable<Long> listImageIds();
}
