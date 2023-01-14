package world.deslauriers.service;

import world.deslauriers.domain.Album;
import world.deslauriers.service.dto.AlbumDto;

import java.util.Optional;

public interface AlbumService {
    Iterable<Album> getAll();

    Optional<AlbumDto> getThumbnailsByAlbum(String album);
}
