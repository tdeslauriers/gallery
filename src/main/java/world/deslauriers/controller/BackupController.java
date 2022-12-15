package world.deslauriers.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import world.deslauriers.domain.Image;
import world.deslauriers.service.ImageService;

@Secured(SecurityRule.IS_AUTHENTICATED)
@ExecuteOn(TaskExecutors.IO)
@Controller("/backup")
public class BackupController {

    private static final Logger log = LoggerFactory.getLogger(BackupController.class);

    @Inject
    private final ImageService imageService;

    public BackupController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Secured({"COLD_STORAGE"})
    @Get
    public Iterable<Image> backup(){

        return imageService.backupAll();
    }
}
