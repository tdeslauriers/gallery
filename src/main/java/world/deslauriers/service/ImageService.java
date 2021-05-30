package world.deslauriers.service;

import world.deslauriers.service.dto.ImageDto;

import java.util.List;

public interface ImageService {

    List<ImageDto> getAll();

    List<ImageDto> getImagesByAlbum(String album);
}
