package world.deslauriers.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.Album;
import world.deslauriers.service.dto.AlbumDto;
import world.deslauriers.service.dto.RestoreAlbum;
import world.deslauriers.service.dto.ThumbnailDto;

public interface AlbumService {
    Flux<Album> getAll();

    Flux<ThumbnailDto> getThumbnailsByAlbum(String album);

    Mono<Void> saveRestoredAlbum(RestoreAlbum restoreAlbum);

    Mono<Album> saveAlbum(AlbumDto cmd);

    Mono<Album> updateAlbum(Album album);

    Flux<Album> getPopulated();
}
