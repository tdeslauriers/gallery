package world.deslauriers.service;

import world.deslauriers.service.dto.AlbumDto;

import java.util.Optional;

public interface AlbumService {
    Optional<AlbumDto> getByAlbum(String album);
}
