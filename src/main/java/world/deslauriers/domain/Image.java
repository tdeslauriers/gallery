package world.deslauriers.domain;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.data.jdbc.annotation.JoinTable;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Set;

@Introspected
@MappedEntity
public record Image(

	@Id @GeneratedValue Long id,
	String filename, // string of UUID
	@Nullable String title,
	@Nullable String description,
	@NonNull LocalDate date,
	@NonNull Boolean published,

	byte[] thumbnail,

	byte[] image,

	@Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "image")
	@JoinTable(name = "album_image")
	Set<AlbumImage> albumImages

){
	public Image(String filename, @Nullable String title, @Nullable String description, @NonNull LocalDate date, @NonNull Boolean published, byte[] thumbnail, byte[] image) {
		this(null, filename, title, description, date, published, thumbnail, image, null);
	}
}
