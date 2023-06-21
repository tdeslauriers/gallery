package world.deslauriers.service.dto;

import io.micronaut.serde.annotation.Serdeable;

import javax.validation.constraints.NotNull;

@Serdeable
public record RestoreAlbumImage(
    @NotNull Long id,
    @NotNull Long album_id,
    @NotNull Long image_id
) {
}
