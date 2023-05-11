package world.deslauriers.service;

import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.Album;
import world.deslauriers.repository.AlbumRepository;
import world.deslauriers.service.dto.AlbumDto;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class AlbumServiceImpl implements AlbumService {

    private static final Logger log = LoggerFactory.getLogger(AlbumServiceImpl.class);

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Flux<Album> getAll(){
        return albumRepository.findAllOrderByAlbumDesc();
    }


    @Override
    public Mono<AlbumDto> getThumbnailsByAlbum(String album) {

        return albumRepository.findThumbnailsByAlbum(album)
                .switchIfEmpty(Mono.empty())
                .collect(Collectors.toSet())
                .map(thumbnailDtos -> new AlbumDto(album, new HashSet<>(thumbnailDtos)));
    }
}
