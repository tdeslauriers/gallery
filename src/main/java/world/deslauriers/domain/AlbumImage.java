package world.deslauriers.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.data.jdbc.annotation.JoinTable;

@Introspected
@MappedEntity
public record AlbumImage(

    @Id @GeneratedValue Long id,

    @Nullable
    @JsonIgnore
    @Relation(Relation.Kind.MANY_TO_ONE)
    @JoinTable(name = "album")
    Album album,

    @Nullable
    @Relation(Relation.Kind.MANY_TO_ONE)
    @JoinTable(name = "image")
    Image image
) {
    public AlbumImage(@Nullable Album album, @Nullable Image image) {
        this(null, album, image);
    }
}
