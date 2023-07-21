package world.deslauriers.service.dto;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import world.deslauriers.domain.Album;

import java.util.Set;

@Serdeable
public record ImageUpdateDto(
        String filename, // uuid
        String title,
        String description,
        Boolean published,
        @Nullable Set<Album> albums
) {
    public ImageUpdateDto(String filename, String title, String description, Boolean published) {
        this(filename, title, description, published, null);
    }
}
