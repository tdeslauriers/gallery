package world.deslauriers.service;

import world.deslauriers.domain.Image;

import java.util.Optional;

public interface ImageService {
    Optional<Image> getImageByFilename(String filename);
}
