package world.deslauriers.service;

import world.deslauriers.domain.Image;
import world.deslauriers.service.dto.ImageUpdateDto;

import java.sql.SQLException;
import java.util.Optional;

public interface ImageService {
    Optional<Image> getImageByFilename(String filename);

    Image updateImage(ImageUpdateDto img) throws SQLException;


}
