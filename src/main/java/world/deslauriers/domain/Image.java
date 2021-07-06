package world.deslauriers.domain;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.GeneratedValue.Type;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.data.annotation.Relation.Kind;
import io.micronaut.data.jdbc.annotation.JoinTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Introspected
@MappedEntity(value = "image")
public class Image implements Serializable {

	private static final long serialVersionUID = 1431559123159076827L;
	
	@Id
	@GeneratedValue(value = Type.IDENTITY)
	private Long id;

	private byte[] filename; // uuid must by byte array for mysql binary(16)

	@Nullable
	private String title;

	@Nullable
	private String description;
	
	@NotNull
	private LocalDate date;
	private Boolean published;

	@Relation(value = Kind.MANY_TO_ONE)
	@JoinTable(name = "album")
	private Album album;

	public Image(byte[] filename, @Nullable String title, @Nullable String description, LocalDate date, Boolean published, Album album) {
		this.filename = filename;
		this.title = title;
		this.description = description;
		this.date = date;
		this.published = published;
		this.album = album;
	}
}
