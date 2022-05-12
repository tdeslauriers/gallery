package world.deslauriers.service;

import world.deslauriers.domain.Image;
import world.deslauriers.service.dto.ImageDto;

public interface ImageService {

    ImageDto loadImageDto(Image pic);
}
