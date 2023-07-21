package world.deslauriers.service.dto;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AlbumDto(
        @Nullable Long id,
        String album,
        String description
) {
    public AlbumDto(Long id, String album) {
        this(id, album, null);
    }
}
