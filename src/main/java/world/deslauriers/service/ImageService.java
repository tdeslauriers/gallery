package world.deslauriers.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.Image;
import world.deslauriers.service.dto.*;

public interface ImageService {

    Flux<ThumbnailDto> getAllUnpublished();

    Flux<Long> listImageIds();

    Mono<ImageDto> getImageByUuid(String filename);

    Mono<Image> getByFilename(String filename);

    Mono<Image> updateImage(ImageUpdateDto img);

    Mono<FullResolutionDto> getFullResolution(String filename);

    Mono<Image> getImageById(Long id);

    Mono<Void> deleteImage(String filename);

    Mono<Void> restoreImage(RestoreImage restoreImage);
}
