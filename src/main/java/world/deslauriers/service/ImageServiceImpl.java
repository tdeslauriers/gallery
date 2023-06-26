package world.deslauriers.service;

import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.Image;
import world.deslauriers.repository.AlbumImageRepository;
import world.deslauriers.repository.ImageRepository;
import world.deslauriers.service.dto.FullResolutionDto;
import world.deslauriers.service.dto.ImageUpdateDto;
import world.deslauriers.service.dto.RestoreImage;
import world.deslauriers.service.dto.ThumbnailDto;

@Singleton
public class ImageServiceImpl implements ImageService {

    private static final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final ImageRepository imageRepository;
    private final AlbumImageRepository albumImageRepository;

    public ImageServiceImpl(ImageRepository imageRepository, AlbumImageRepository albumImageRepository) {
        this.imageRepository = imageRepository;
        this.albumImageRepository = albumImageRepository;
    }

    @Override
    public Flux<ThumbnailDto> getAllUnpublished(){
        return imageRepository.findAllUnpublished();
    }

    @Override
    public Flux<Long> listImageIds(){
        return imageRepository.findAllImageIds();
    }

    @Override
    public Mono<Image> getImageByUuid(String filename){
        return Mono.from(imageRepository
                .findByUuid(filename)
                .flatMap(imageDto -> Mono.just(new Image(
                        imageDto.id(),
                        imageDto.filename(),
                        imageDto.title(),
                        imageDto.description(),
                        imageDto.date(),
                        imageDto.published(),
                        imageDto.thumbnail(),
                        imageDto.presentation())))
                .flatMapMany(image -> Mono.from(albumImageRepository
                        .findByImageId(image.getId())
                        .flatMap(albumImage -> {
                            image.getAlbumImages().add(albumImage);
                            return Mono.just(image);
                        }))));
    }

    @Override
    public Mono<Image> getByFilename(String filename){
        return imageRepository.findByFilename(filename);
    }

    @Override
    public Mono<Image> updateImage(ImageUpdateDto img) {
        return imageRepository
                .findById(img.id())
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("Attempt to update image that does not exist: {}", img.id());
                    return Mono.empty();
                }))
                .flatMap(image -> {
                    image.setTitle(img.title());
                    image.setDescription(img.description());
                    image.setPublished(img.published());
                    return imageRepository.update(image);
                });
    }

    @Override
    public Mono<FullResolutionDto> getFullResolution(String filename){
        return imageRepository.findFullResolutionByFilename(filename);
    }

    @Override
    public Mono<Image> getImageById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteImage(String filename){

        return getImageByUuid(filename)
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("Attempting to delete an image that does not exist");
                    return Mono.empty();
                }))
                .flatMap(image -> {
                     var albumimages = Flux.fromStream(image.getAlbumImages().stream());
                     var delComplete = albumimages
                             .concatMap(albumImage -> {
                                 log.info("Deleting xref id: {} between image: {} and album {}.", albumImage.id(), image.getFilename(), albumImage.album().album());
                                 return albumImageRepository.delete(albumImage);
                             })
                             .then();
                     delComplete.subscribe(
                             unused -> log.info("xref deletion activities complete."),
                             error -> log.error("failed to delete all xrefs")
                     );

                    return Mono.just(image);
                })
                .flatMap(image -> {
                    log.info("Deleting image {}: {}", image.getId(), image.getFilename());
                    return imageRepository.delete(image);
                })
                .flatMap(id -> {
                    log.info("Image id: {} successfully deleted", id);
                    return Mono.empty();
                })
                .then();
    }

    @Override
    public Mono<Void> restoreImage(RestoreImage restoreImage) {
        return imageRepository.saveRestoreImage(
                restoreImage.id(),
                restoreImage.filename(),
                restoreImage.title(),
                restoreImage.description(),
                restoreImage.date(),
                restoreImage.published(),
                restoreImage.thumbnail(),
                restoreImage.presentation(),
                restoreImage.image()
        );
    }
}
