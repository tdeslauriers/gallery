package world.deslauriers.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import world.deslauriers.service.ImageService;
import world.deslauriers.service.dto.ImageDto;

import javax.inject.Inject;
import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/images")
public class ImageController {

    @Inject
    protected final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Get
    public List<ImageDto> getAll(){

        return imageService.getAll();
    }

    @Get("/{album}")
    public List<ImageDto> getByAlbum(String album){

        return imageService.getImagesByAlbum(album);
    }
}
