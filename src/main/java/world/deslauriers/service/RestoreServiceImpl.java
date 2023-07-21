package world.deslauriers.service;

import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import world.deslauriers.service.dto.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public Mono<Void> restoreAlbum(AlbumDto backupAlbum) {

        // TODO: add decryption/type-conversion logic after initial data restore.
        // TODO: add check to see if record exists, or is more current than backup.
        // temp

        return albumService.saveRestoredAlbum(new RestoreAlbum(backupAlbum.id(), backupAlbum.album()));
    }

    @Override
    public Mono<Void> restoreImage(BackupImage backupImage) {

        // TODO: add decryption/type-conversion logic after initial data restore.
        // TODO: add check to see if record exists, or is more current than backup.
        // temp

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        var date = LocalDateTime.parse(backupImage.date(), formatter).toLocalDate();

//        byte[] thumbnail = Base64.getDecoder().decode(backupImage.thumbnail());
//        byte[] presentation = Base64.getDecoder().decode(backupImage.presentation());
//        byte[] image = Base64.getDecoder().decode(backupImage.image());

        return imageService.getByFilename(backupImage.filename())
                .flatMap(image -> imageService.updateImage(new ImageUpdateDto(
                        image.getFilename(),
                        backupImage.title(),
                        backupImage.description(),
                        backupImage.published()
                )))
                .then();
    }

    @Override
    public Mono<Void> restoreAlbumImage(BackupAlbumImage backupAlbumImage) {
        return albumImageService.restoreAlbumImage(new AlbumImageDto(backupAlbumImage.id(), backupAlbumImage.album_id(), backupAlbumImage.image_id()));
    }
}
