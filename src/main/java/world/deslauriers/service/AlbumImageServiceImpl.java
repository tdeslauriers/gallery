package world.deslauriers.service;

import jakarta.inject.Singleton;
import reactor.core.publisher.Mono;
import world.deslauriers.repository.AlbumImageRepository;
import world.deslauriers.service.dto.RestoreAlbumImage;

@Singleton
public class AlbumImageServiceImpl implements AlbumImageService {

    private final AlbumImageRepository albumImageRepository;

    public AlbumImageServiceImpl(AlbumImageRepository albumImageRepository) {
        this.albumImageRepository = albumImageRepository;
    }

    @Override
    public Mono<Void> restoreAlbumImage(RestoreAlbumImage restoreAlbumImage) {
        return albumImageRepository.saveRestoreAlbumImage(restoreAlbumImage.id(), restoreAlbumImage.album_id(), restoreAlbumImage.image_id());
    }
}
