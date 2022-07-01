package world.deslauriers.service.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;

import java.time.LocalDate;

@Introspected
public record ThumbnailDto(
        Long id,
        String filename,
        @Nullable String title,
        @Nullable String description,
        LocalDate date,
        Boolean published,
        byte[] thumbnail
) {}
