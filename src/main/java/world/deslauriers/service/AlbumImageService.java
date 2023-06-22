package world.deslauriers.service;

import reactor.core.publisher.Mono;
import world.deslauriers.service.dto.RestoreAlbumImage;

public interface AlbumImageService {
    Mono<Void> restoreAlbumImage(RestoreAlbumImage restoreAlbumImage);
}
