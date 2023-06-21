package world.deslauriers.service;

import reactor.core.publisher.Mono;
import world.deslauriers.domain.Album;
import world.deslauriers.domain.AlbumImage;
import world.deslauriers.domain.Image;
import world.deslauriers.service.dto.BackupAlbum;
import world.deslauriers.service.dto.BackupAlbumImage;
import world.deslauriers.service.dto.BackupImage;

public interface RestoreService {
    Mono<Album> restoreAlbum(BackupAlbum backupAlbum);

    Mono<Image> restoreImage(BackupImage backupImage);

    Mono<AlbumImage> restoreAlbumImage(BackupAlbumImage backupAlbumImage);
}
