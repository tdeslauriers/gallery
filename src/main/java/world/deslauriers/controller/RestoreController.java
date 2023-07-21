package world.deslauriers.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import reactor.core.publisher.Mono;
import world.deslauriers.service.RestoreService;
import world.deslauriers.service.dto.AlbumDto;
import world.deslauriers.service.dto.BackupAlbumImage;
import world.deslauriers.service.dto.BackupImage;

@Secured({"COLD_STORAGE"})
@Controller("/restore")
public class RestoreController {

    private final RestoreService restoreService;

    public RestoreController(RestoreService restoreService) {
        this.restoreService = restoreService;
    }

    @Post("/album")
    public Mono<HttpResponse<?>> restoreAlbum(@Body AlbumDto backupAlbum){
        return restoreService
                .restoreAlbum(backupAlbum)
                .thenReturn(HttpResponse.noContent());
    }

    @Post("/image")
    public Mono<HttpResponse<?>> restoreImage(@Body BackupImage backupImage){
        return restoreService
                .restoreImage(backupImage)
                .thenReturn(HttpResponse.noContent());
    }

    @Post("/album_image")
    public Mono<HttpResponse<?>> restoreAlbumImage(@Body BackupAlbumImage backupAlbumImage){
        return restoreService
                .restoreAlbumImage(backupAlbumImage)
                .thenReturn(HttpResponse.noContent());
    }
}
