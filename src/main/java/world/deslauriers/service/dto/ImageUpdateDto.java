package world.deslauriers.service.dto;

import java.util.HashSet;

public record ImageUpdateDto(
        Long id,
        String title,
        String description,
        Boolean published
) {}
