package world.deslauriers.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import world.deslauriers.domain.Album;
import world.deslauriers.repository.AlbumRepository;
import world.deslauriers.service.dto.AlbumDto;

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
    public Flux<Album> getAll(){
        return albumRepository.findAllOrderByAlbumDesc();
    }


    @Override
    public Optional<AlbumDto> getThumbnailsByAlbum(String album) {

        var thumbs = albumRepository.findThumbnailsByAlbum(album);
        if (thumbs.size() <= 0){
            return Optional.empty();
        }

        return Optional.of(new AlbumDto(album, thumbs));
    }

}
