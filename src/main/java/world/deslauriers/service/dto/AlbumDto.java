package world.deslauriers.service.dto;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

import java.util.HashSet;

@Serdeable
public record AlbumDto(
        String album,
        @Nullable String description,
        HashSet<ThumbnailDto> thumbnails
) {
    public AlbumDto(String album, HashSet<ThumbnailDto> thumbnails) {
        this(album, null, thumbnails);
    }
}
