package world.deslauriers.service.dto;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record FullResolutionDto(
        String filename,
        byte[] image
) {
}
