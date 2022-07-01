package world.deslauriers.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import world.deslauriers.domain.Album;
import world.deslauriers.domain.Image;
import world.deslauriers.repository.AlbumRepository;
import world.deslauriers.service.dto.AlbumDto;
import world.deslauriers.service.dto.ThumbnailDto;

import java.util.HashSet;
import java.util.Optional;

@Singleton
public class AlbumServiceImpl implements AlbumService {

    private static final Logger log = LoggerFactory.getLogger(AlbumServiceImpl.class);

    @Inject
    private final AlbumRepository albumRepository;

    @Inject
    private final ImageService imageService;

    public AlbumServiceImpl(AlbumRepository albumRepository, ImageService imageService) {
        this.albumRepository = albumRepository;
        this.imageService = imageService;
    }

    @Override
    public Iterable<Album> getAll(){
        return albumRepository.findAll();
    }

    @Override
    public Optional<AlbumDto> getByAlbum(String album) {



        return Optional.empty();
    }


}
