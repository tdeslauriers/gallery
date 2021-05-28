package world.deslauriers.service;

import world.deslauriers.domain.Album;

import java.util.Optional;

public interface AlbumService {
    Optional<Album> getByAlbum(String album);
}
