package world.deslauriers.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.Image;
import world.deslauriers.service.ImageService;

@Secured({"COLD_STORAGE"})
@Controller("/backup")
public class BackupController {

    private static final Logger log = LoggerFactory.getLogger(BackupController.class);

    private final ImageService imageService;

    public BackupController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Get("/list")
    Flux<Long> listImageIds(){
        return imageService.listImageIds();
    }

    @Get("/{id}")
    public Mono<Image> getImage(Long id){
        return imageService.getImageById(id);
    }

}
