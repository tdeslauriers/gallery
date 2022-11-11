package world.deslauriers.service.dto;

public record ImageUpdateDto(
        Long id,
        String title,
        String description,
        Boolean published
) {}
