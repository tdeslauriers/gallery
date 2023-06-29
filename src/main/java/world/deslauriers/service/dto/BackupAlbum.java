package world.deslauriers.service.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record BackupAlbum(
        Long id,
        String album,
        String description
) {
    public BackupAlbum(Long id, String album) {
        this(id, album, null);
    }
}
