package world.deslauriers.service.dto;

import io.micronaut.core.annotation.Introspected;
import world.deslauriers.domain.Image;

import java.util.HashSet;
import java.util.Set;

@Introspected
public record AlbumDto(
        String album,
        HashSet<ThumbnailDto> thumbnails
) {}
