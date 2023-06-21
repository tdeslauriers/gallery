package world.deslauriers.service;

import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.Album;
import world.deslauriers.domain.AlbumImage;
import world.deslauriers.domain.Image;
import world.deslauriers.service.dto.*;

@Singleton
public class RestoreServiceImpl implements RestoreService {

    private static final Logger log = LoggerFactory.getLogger(RestoreServiceImpl.class);

    private final AlbumService albumService;
    private final ImageService imageService;
    private final AlbumImageService albumImageService;

    public RestoreServiceImpl(AlbumService albumService, ImageService imageService, AlbumImageService albumImageService) {
        this.albumService = albumService;
        this.imageService = imageService;
        this.albumImageService = albumImageService;
    }

    @Override
    public Mono<Album> restoreAlbum(BackupAlbum backupAlbum) {

        // TODO: add decryption/type-conversion logic after initial data restore.
        // TODO: add check to see if record exists, or is more current than backup.
        // temp

        return albumService.saveRestoredAlbum(new RestoreAlbum(backupAlbum.id(), backupAlbum.album()));
    }

    @Override
    public Mono<Image> restoreImage(BackupImage backupImage) {

        // TODO: add decryption/type-conversion logic after initial data restore.
        // TODO: add check to see if record exists, or is more current than backup.
        // temp
        return imageService.restoreImage(new RestoreImage(
                backupImage.id(),
                backupImage.filename(),
                backupImage.title(),
                backupImage.description(),
                backupImage.date(),
                backupImage.published(),
                backupImage.thumbnail(),
                backupImage.presentation(),
                backupImage.image()
        ));
    }

    @Override
    public Mono<AlbumImage> restoreAlbumImage(BackupAlbumImage backupAlbumImage) {
        return albumImageService.restoreAlbumImage(new RestoreAlbumImage(backupAlbumImage.id(), backupAlbumImage.album_id(), backupAlbumImage.image_id()));
    }
}
