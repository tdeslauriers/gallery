package world.deslauriers.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import world.deslauriers.domain.Image;
import world.deslauriers.service.ImageService;

import java.util.Optional;

@Secured(SecurityRule.IS_AUTHENTICATED)
@ExecuteOn(TaskExecutors.IO)
@Controller("/images")
public class ImageController {

    @Inject
    protected final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Secured({"GALLERY_READ", "GALLERY_EDIT"})
    @Get("/{filename}")
    public Optional<Image> getImage(String filename){

        return imageService.getImageByFilename(filename);
    }
}
