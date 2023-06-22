package world.deslauriers.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.Image;
import world.deslauriers.service.dto.FullResolutionDto;
import world.deslauriers.service.dto.ImageUpdateDto;
import world.deslauriers.service.dto.RestoreImage;
import world.deslauriers.service.dto.ThumbnailDto;

public interface ImageService {

    Flux<ThumbnailDto> getAllUnpublished();

    Flux<Long> listImageIds();

    Mono<Image> getImageByFilename(String filename);

    Mono<Image> updateImage(ImageUpdateDto img);

    Mono<FullResolutionDto> getFullResolution(String filename);

    Mono<Image> getImageById(Long id);

    Mono<Void> deleteImage(String filename);

    Mono<Void> restoreImage(RestoreImage restoreImage);
}
