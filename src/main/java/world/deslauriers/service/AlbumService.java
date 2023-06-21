package world.deslauriers.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.Album;
import world.deslauriers.service.dto.AlbumDto;
import world.deslauriers.service.dto.RestoreAlbum;

public interface AlbumService {
    Flux<Album> getAll();

    Mono<AlbumDto> getThumbnailsByAlbum(String album);

    Mono<Album> saveRestoredAlbum(RestoreAlbum restoreAlbum);
}
