package world.deslauriers.service;

import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.AlbumImage;
import world.deslauriers.repository.AlbumImageRepository;
import world.deslauriers.service.dto.AlbumImageDto;

@Singleton
public class AlbumImageServiceImpl implements AlbumImageService {

    private static final Logger log = LoggerFactory.getLogger(AlbumServiceImpl.class);

    private final AlbumImageRepository albumImageRepository;

    public AlbumImageServiceImpl(AlbumImageRepository albumImageRepository) {
        this.albumImageRepository = albumImageRepository;
    }

    @Override
    public Mono<Void> restoreAlbumImage(AlbumImageDto restoreAlbumImage) {
        return albumImageRepository.saveRestoreAlbumImage(restoreAlbumImage.id(), restoreAlbumImage.album_id(), restoreAlbumImage.image_id());
    }

    @Override
    public Mono<Long> deleteAlbumImage(AlbumImageDto cmd) {

        return albumImageRepository
                .findByImageIdAndAlbumId(cmd.image_id(), cmd.album_id())
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("Attempting to delete xref that does not exist: {}", cmd);
                    return Mono.empty();
                }))
                .flatMap(albumImage -> {
                    log.info("Removing xref {}: between album {} and image {} - {} ", albumImage.id(), albumImage.album().album(), albumImage.image().getFilename(), albumImage.image().getTitle());
                    return albumImageRepository.delete(albumImage);
                });
    }
}
