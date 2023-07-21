package world.deslauriers.service;

import io.micronaut.data.exceptions.EmptyResultException;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.Album;
import world.deslauriers.repository.AlbumRepository;
import world.deslauriers.repository.ImageRepository;
import world.deslauriers.service.dto.AlbumDto;
import world.deslauriers.service.dto.RestoreAlbum;
import world.deslauriers.service.dto.ThumbnailDto;

@Singleton
public class AlbumServiceImpl implements AlbumService {

    private static final Logger log = LoggerFactory.getLogger(AlbumServiceImpl.class);

    private final AlbumRepository albumRepository;
    private final ImageRepository imageRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository, ImageRepository imageRepository) {
        this.albumRepository = albumRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public Flux<Album> getAll(){
        return albumRepository.findAllOrderByAlbumDesc();
    }

    @Override
    public Flux<Album> getPopulated() {
        return albumRepository.findPopulated();
    }

    @Override
    public Flux<ThumbnailDto> getThumbnailsByAlbum(String album) {

        return imageRepository.findThumbnailsByAlbum(album)
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("There are no images in this album. {}", album.substring(0, 5));
                    return Mono.empty();
                }));
    }

    @Override
    public Mono<Void> saveRestoredAlbum(RestoreAlbum restoreAlbum) {
                return albumRepository.saveRestoreAlbum(restoreAlbum.id(), restoreAlbum.album(), restoreAlbum.description());
    }

    @Override
    public Mono<Album> saveAlbum(AlbumDto cmd) {
        return albumRepository
                .existsByAlbum(cmd.album())
                .flatMap(exists -> {
                    if (exists){
                        return Mono.error(new IllegalStateException("Album with this name already exists."));
                    } else {
                        return albumRepository.save(new Album(cmd.album(), cmd.description()));
                    }
                });
    }

    @Override
    public Mono<Album> updateAlbum(Album album) {
        return albumRepository
                .existsById(album.id())
                .flatMap(exists -> {
                    if (!exists){
                        log.error("No album with id: {} exists, cannot update.", album.id());
                        return Mono.error(new IllegalStateException());
                    } else {
                        log.info("Album id: {} updated to {}: {}", album.id(), album.album(), album.description());
                        return albumRepository.update(album);
                    }
                });
    }

}
