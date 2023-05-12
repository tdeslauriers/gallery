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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.Image;
import world.deslauriers.service.ImageService;

import java.util.Optional;

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
    @Get("/list")
    Flux<Long> listImageIds(){
        return imageService.listImageIds();
    }

    @Secured({"COLD_STORAGE"})
    @Get("/{id}")
    public Mono<Image> getImage(Long id){
        return imageService.getImageById(id);
    }

}
