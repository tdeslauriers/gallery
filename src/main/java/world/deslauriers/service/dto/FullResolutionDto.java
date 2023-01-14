package world.deslauriers.service.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record FullResolutionDto(
        String filename,
        byte[] image
) {
}
