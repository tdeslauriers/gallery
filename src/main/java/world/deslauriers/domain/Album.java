package world.deslauriers.domain;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.serde.annotation.Serdeable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Serdeable
@MappedEntity
public record Album(

	@Id @GeneratedValue	Long id,
	@NotNull @Size(min = 2, max = 32) String album,

	@Nullable @Size(max = 128) String description,

	@Nullable
	@Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "album")
	Set<AlbumImage> albumImages

){
	public Album(String album) {
		this(null, album, null, null);
	}

	public Album(String album, @Nullable String description) {
		this(null, album, description, null);
	}

	public Album(Long id, String album, @Nullable String description) {
		this(id, album, description, null);
	}
}
