package world.deslauriers.service;

import reactor.core.publisher.Mono;
import world.deslauriers.domain.AlbumImage;
import world.deslauriers.service.dto.RestoreAlbumImage;

public interface AlbumImageService {
    Mono<AlbumImage> restoreAlbumImage(RestoreAlbumImage restoreAlbumImage);
}
