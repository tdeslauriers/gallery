package world.deslauriers.domain;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.GeneratedValue.Type;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.data.jdbc.annotation.JoinTable;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Introspected
@MappedEntity
public record Image(

	@Id @GeneratedValue Long id,
	byte[] filename, // uuid must by byte array for mysql binary(16)
	@Nullable String title,
	@Nullable String description,
	@NonNull LocalDate date,
	@NonNull Boolean published,

	@Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "image")
	@JoinTable(name = "album_image")
	Set<AlbumImage> albumImages

){
	public Image(byte[] filename, @Nullable String title, @Nullable String description, @NonNull LocalDate date, @NonNull Boolean published, Set<AlbumImage> albumImages) {
		this(null, filename, title, description, date, published, albumImages);
	}
}
