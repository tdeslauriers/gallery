package world.deslauriers.domain;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.serde.annotation.Serdeable;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Serdeable
@MappedEntity
public record Album(

	@Id @GeneratedValue	Long id,
	@NotNull String album,

	@Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "album")
	Set<AlbumImage> albumImages

){
	public Album(String album) {
		this(null, album, null);
	}

	public Album(Long id, String album) {
		this(id, album, null);
	}
}
