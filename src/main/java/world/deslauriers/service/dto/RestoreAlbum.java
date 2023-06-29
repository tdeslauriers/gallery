package world.deslauriers.service.dto;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record RestoreAlbum(
        Long id,
        String album,
        @Nullable String description
) {
    public RestoreAlbum(Long id, String album) {
        this(id, album, null);
    }
}
