package world.deslauriers.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.Image;
import world.deslauriers.service.ImageService;
import world.deslauriers.service.dto.FullResolutionDto;
import world.deslauriers.service.dto.ImageUpdateDto;
import world.deslauriers.service.dto.ThumbnailDto;

import javax.validation.constraints.Size;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/images")
public class ImageController {

    protected final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Secured({"GALLERY_READ", "GALLERY_EDIT", "COLD_STORAGE"})
    @Get("/{filename}")
    public Mono<Image> getImage(String filename){
        return imageService.getImageByUuid(filename);
    }

    @Secured({"GALLERY_READ", "GALLERY_EDIT"})
    @Get("/fullresolution/{filename}")
    public Mono<FullResolutionDto> getFullResoltuion(String filename){
        return imageService.getFullResolution(filename);
    }

    @Secured({"GALLERY_EDIT"})
    @Get("/unpublished")
    public Flux<ThumbnailDto> getAllUnpublished(){
        return imageService.getAllUnpublished();
    }

    @Secured({"GALLERY_EDIT"})
    @Put
    public Mono<HttpResponse<?>> update(@Body ImageUpdateDto cmd){
        return imageService
                .updateImage(cmd)
                .map(r -> HttpResponse
                        .noContent());
    }

    @Secured({"GALLERY_EDIT"})
    @Delete("/{filename}")
    @Status(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@Size(min = 2, max = 64) String filename){
        return imageService.deleteImage(filename)
                .then();
    }
}
