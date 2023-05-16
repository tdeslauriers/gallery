package world.deslauriers.service;

import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.Album;
import world.deslauriers.repository.AlbumRepository;
import world.deslauriers.repository.ImageRepository;
import world.deslauriers.service.dto.AlbumDto;

import java.util.HashSet;
import java.util.stream.Collectors;

@Singleton
public class AlbumServiceImpl implements AlbumService {

    private static final Logger log = LoggerFactory.getLogger(AlbumServiceImpl.class);

    private final AlbumRepository albumRepository;
    private final ImageRepository imageRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository, ImageRepository imageRepository) {
        this.albumRepository = albumRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public Flux<Album> getAll(){
        return albumRepository.findAllOrderByAlbumDesc();
    }


    @Override
    public Mono<AlbumDto> getThumbnailsByAlbum(String album) {

        return imageRepository.findThumbnailsByAlbum(album)
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("There are no images in this album. {}", album.substring(0, 5));
                    return Mono.empty();
                }))
                .collect(Collectors.toSet())
                .map(thumbnailDtos -> new AlbumDto(album, new HashSet<>(thumbnailDtos)));
    }
}
