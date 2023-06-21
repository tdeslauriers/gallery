package world.deslauriers.service.dto;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Serdeable
public record RestoreImage(
        @NotNull Long id,
        @NonNull String filename, // string of UUID
        @Nullable String title,
        @Nullable String description,
        @NonNull LocalDate date,
        @NonNull Boolean published,
        @NotNull byte[] thumbnail,
        @NotNull byte[] presentation,
        @NotNull byte[] image
) {
}
