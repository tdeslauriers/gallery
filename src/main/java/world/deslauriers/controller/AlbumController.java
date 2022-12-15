package world.deslauriers.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import world.deslauriers.domain.Album;
import world.deslauriers.service.AlbumService;
import world.deslauriers.service.dto.AlbumDto;
import world.deslauriers.service.dto.ThumbnailDto;

import javax.validation.constraints.Size;
import java.util.Optional;

@Secured(SecurityRule.IS_AUTHENTICATED)
@ExecuteOn(TaskExecutors.IO)
@Controller("/albums")
public class AlbumController {

    @Inject
    protected final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    // drawer/menu
    @Secured({"GALLERY_READ", "GALLERY_EDIT"})
    @Get
    public Iterable<Album> getAll(){
        return albumService.getAll();
    }

    @Secured({"GALLERY_READ", "GALLERY_EDIT"})
    @Get("/{album}")
    public Optional<AlbumDto> getByAlbum(@Size(min = 2, max = 32) String album){

        return albumService.getThumbnailsByAlbum(album);
    }
}
