package world.deslauriers.service;

import world.deslauriers.domain.Image;
import world.deslauriers.service.dto.ThumbnailDto;

public interface ImageService {

    ThumbnailDto loadThumbnailDto(Image pic);
}
