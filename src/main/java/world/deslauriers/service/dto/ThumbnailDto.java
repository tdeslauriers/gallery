package world.deslauriers.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;

import java.time.LocalDate;

@Introspected
public record ThumbnailDto(
        Long id,
        String filename,
        @Nullable String title,
        @Nullable String description,
        @JsonFormat(pattern="yyyy-MM-dd") LocalDate date,
        Boolean published,
        byte[] thumbnail
) {}
