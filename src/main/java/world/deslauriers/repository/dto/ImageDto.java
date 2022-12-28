package world.deslauriers.repository.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;

import java.time.LocalDate;

@Introspected
public record ImageDto(
        Long id,
        String filename, // string of UUID
        @Nullable String title,
        @Nullable String description,
        @JsonFormat(pattern="yyyy-MM-dd") @NonNull LocalDate date,
        @NonNull Boolean published,
        byte[] thumbnail,
        byte[] presentation

) {
}
