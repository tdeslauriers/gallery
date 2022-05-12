package world.deslauriers.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import world.deslauriers.service.ImageService;

@Secured(SecurityRule.IS_AUTHENTICATED)
@ExecuteOn(TaskExecutors.IO)
@Controller("/images")
public class ImageController {

    @Inject
    protected final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    // placeholder
}
