package world.deslauriers.service.dto;

import io.micronaut.core.annotation.Introspected;

import java.time.LocalDate;

@Introspected
public record ThumbnailDto(
        Long id,
        String filename,
        String title,
        String description,
        LocalDate date,
        Boolean published,
        byte[] thumbnail
) {}
