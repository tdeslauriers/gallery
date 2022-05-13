package world.deslauriers.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import world.deslauriers.domain.Album;
import world.deslauriers.repository.AlbumRepository;
import world.deslauriers.service.dto.AlbumDto;
import world.deslauriers.service.dto.ImageDto;

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

        var selected = albumRepository.findByAlbum(album);
        if (selected.isPresent()){
            var images = new HashSet<ImageDto>(selected.get().albumImages().size());
            selected.get().albumImages().forEach(albumImage -> {
                if (albumImage.image().published()) images.add(imageService.loadImageDto(albumImage.image()));
            });
            return Optional.of(new AlbumDto(selected.get().id(), selected.get().album(), images));
        }
        return Optional.empty();
    }
}
