package world.deslauriers.service.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record ImageUpdateDto(
        Long id,
        String title,
        String description,
        Boolean published
) {}
