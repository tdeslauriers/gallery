package world.deslauriers.service;

import reactor.core.publisher.Mono;
import world.deslauriers.service.dto.AlbumImageDto;

public interface AlbumImageService {
    Mono<Void> restoreAlbumImage(AlbumImageDto dto);

    Mono<Long> deleteAlbumImage(AlbumImageDto cmd);
}
