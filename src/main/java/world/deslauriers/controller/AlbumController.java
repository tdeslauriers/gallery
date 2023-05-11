package world.deslauriers.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.Album;
import world.deslauriers.service.AlbumService;
import world.deslauriers.service.dto.AlbumDto;

import javax.validation.constraints.Size;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/albums")
public class AlbumController {

    protected final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    // drawer menu
    @Secured({"GALLERY_READ", "GALLERY_EDIT"})
    @Get
    public Flux<Album> getAll(){
        return albumService.getAll();
    }

    @Secured({"GALLERY_READ", "GALLERY_EDIT"})
    @Get("/{album}")
    public Mono<AlbumDto> getByAlbum(@Size(min = 2, max = 32) String album){
        return albumService.getThumbnailsByAlbum(album);
    }
}
