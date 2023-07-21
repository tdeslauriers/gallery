package world.deslauriers.service;

import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import world.deslauriers.domain.Album;
import world.deslauriers.domain.AlbumImage;
import world.deslauriers.domain.Image;
import world.deslauriers.repository.AlbumImageRepository;
import world.deslauriers.repository.AlbumRepository;
import world.deslauriers.repository.ImageRepository;
import world.deslauriers.service.dto.*;

import java.util.stream.Collectors;

@Singleton
public class ImageServiceImpl implements ImageService {

    private static final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final ImageRepository imageRepository;

    private final AlbumRepository albumRepository;
    private final AlbumImageRepository albumImageRepository;

    public ImageServiceImpl(ImageRepository imageRepository, AlbumRepository albumRepository, AlbumImageRepository albumImageRepository) {
        this.imageRepository = imageRepository;
        this.albumRepository = albumRepository;
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
    public Mono<ImageDto> getImageByUuid(String filename){
        return imageRepository
                .findByUuid(filename)
                .flatMap(presentationDto -> Mono.just(new ImageDto(
                        presentationDto.id(),
                        presentationDto.filename(),
                        presentationDto.title(),
                        presentationDto.description(),
                        presentationDto.date(),
                        presentationDto.published(),
                        presentationDto.thumbnail(),
                        presentationDto.presentation()
                )))
                .flatMap(imageDto -> albumImageRepository
                        .findByImageId(imageDto.getId())
                        .flatMap(albumImage -> Mono.just(albumImage.album()))
                        .collect(Collectors.toSet())
                        .flatMap(albums -> {
                            imageDto.getAlbums().addAll(albums);
                            return Mono.just(imageDto);
                        }));
    }

    @Override
    public Mono<Image> getByFilename(String filename){
        return imageRepository.findByFilename(filename);
    }

    @Override
    public Mono<Image> updateImage(ImageUpdateDto cmd) {
        return imageRepository
                .findByFilename(cmd.filename())
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("Attempt to update image that does not exist: {}", cmd.filename());
                    return Mono.empty();
                }))
                .flatMap(image -> {
                    image.setTitle(cmd.title());
                    image.setDescription(cmd.description());
                    image.setPublished(cmd.published());

                    // resolve album additions
                    // album removals handled by client calls
                    if (cmd.albums() != null){
                        if (cmd.albums().size() < 1){
                            return Mono.error(new Throwable("Image must be associated with at least one album."));
                        } else {
                           Flux.fromStream(cmd.albums().stream())
                                    .flatMap(album -> albumRepository
                                            .findByAlbum(album.album())
                                            .switchIfEmpty(Mono.defer(() -> {
                                                log.error("Album {} does not exist, cannot associate", album.album());
                                                return Mono.empty();
                                            })))
                                    .flatMap(album -> {
                                        if (image.getAlbumImages()
                                                .stream()
                                                .noneMatch(albumImage -> albumImage.album().album().equals(album.album()))){
                                            log.info("Album {} not yet associated with image {}: creating xref", album.album(), image.getFilename());
                                            return albumImageRepository
                                                    .save(new AlbumImage(album, image))
                                                    .flatMap(albumImage -> {
                                                        log.info("Successfully saved xref {}: album {} --> image {} - {}", albumImage.id(), albumImage.album().album(), image.getFilename(), image.getTitle());
                                                        return Mono.just(albumImage.album());
                                                    });
                                        }
                                        return Mono.just(album);
                                    })
                                   .subscribe();
                        }
                    }
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
                .flatMap(imageDto -> {
                    return albumImageRepository
                            .findByImageId(imageDto.getId())
                            .flatMap(albumImage -> {
                                log.info("Deleting xref id: {} between image: {} and album {}.", albumImage.id(), imageDto.getFilename(), albumImage.album());
                                return albumImageRepository.delete(albumImage);
                            })
                            .collectList()
                            .flatMap(longs -> {
                                log.info("Deleting image: {}", imageDto.getFilename());
                                return imageRepository.deleteById(imageDto.getId());
                            });
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
