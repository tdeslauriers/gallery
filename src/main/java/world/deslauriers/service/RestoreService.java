package world.deslauriers.service;

import reactor.core.publisher.Mono;
import world.deslauriers.service.dto.AlbumDto;
import world.deslauriers.service.dto.BackupAlbumImage;
import world.deslauriers.service.dto.BackupImage;

public interface RestoreService {
    Mono<Void> restoreAlbum(AlbumDto backupAlbum);

    Mono<Void> restoreImage(BackupImage backupImage);

    Mono<Void> restoreAlbumImage(BackupAlbumImage backupAlbumImage);
}
