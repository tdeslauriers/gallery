package world.deslauriers.service.dto;

import io.micronaut.serde.annotation.Serdeable;

import java.util.HashSet;

@Serdeable
public record AlbumDto(
        String album,
        HashSet<ThumbnailDto> thumbnails
) {}
