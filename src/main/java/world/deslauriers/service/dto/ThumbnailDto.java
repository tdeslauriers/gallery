package world.deslauriers.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDate;

@Serdeable
public record ThumbnailDto(
        Long id,
        String filename,
        @Nullable String title,
        @Nullable String description,
        @JsonFormat(pattern="yyyy-MM-dd") LocalDate date,
        Boolean published,

        byte[] thumbnail
) {}
