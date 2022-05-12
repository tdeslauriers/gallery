package world.deslauriers.service.dto;

import io.micronaut.core.annotation.Introspected;

import java.util.Set;

@Introspected
public record AlbumDto(
        Long id,
        String album,
        Set<ImageDto> images
) {}
