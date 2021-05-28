package world.deslauriers.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import world.deslauriers.domain.Album;
import world.deslauriers.repository.AlbumRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class AlbumServiceImpl implements AlbumService {

    private static final Logger log = LoggerFactory.getLogger(AlbumServiceImpl.class);

    @Inject
    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Optional<Album> getByAlbum(String album){

        return albumRepository.findByAlbum(album);
    }
}
