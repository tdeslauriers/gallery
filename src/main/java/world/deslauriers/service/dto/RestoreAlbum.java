package world.deslauriers.service.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record RestoreAlbum(
        Long id,
        String album
) {
}
