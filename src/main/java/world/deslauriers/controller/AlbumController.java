package world.deslauriers.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.Album;
import world.deslauriers.service.AlbumService;
import world.deslauriers.service.dto.AlbumDto;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/albums")
public class AlbumController {

    protected final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    // drawer menu
    @Secured({"GALLERY_READ"})
    @Get
    public Flux<Album> getAll(){
        return albumService.getAll();
    }

    @Secured({"GALLERY_READ"})
    @Get("/{album}")
    public Mono<AlbumDto> getByAlbum(@Size(min = 2, max = 16) String album){
        return albumService.getThumbnailsByAlbum(album);
    }

    @Secured({"GALLERY_EDIT"})
    @Post
    public Mono<HttpResponse<Album>> saveAlbum(@Body @Valid Album album){
        return albumService
                .saveAlbum(album)
                .map(HttpResponse::created);

    }

    @Secured({"GALLERY_EDIT"})
    @Put
    Mono<HttpResponse<?>> updateAlbum(@Body @Valid Album album){
        return albumService
                .updateAlbum(album)
                .thenReturn(HttpResponse.noContent());
    }
}
