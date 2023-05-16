package world.deslauriers.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@MappedEntity
public record AlbumImage(

    @Id @GeneratedValue Long id,

    @Nullable
    @Relation(Relation.Kind.MANY_TO_ONE)
    Album album,

    @Nullable
    @JsonIgnore
    @Relation(Relation.Kind.MANY_TO_ONE)
    Image image
) {
    public AlbumImage(@Nullable Album album, @Nullable Image image) {
        this(null, album, image);
    }
}
