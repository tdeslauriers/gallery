package world.deslauriers.domain;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.GeneratedValue.Type;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.data.annotation.Relation.Kind;
import io.micronaut.data.jdbc.annotation.JoinTable;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

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

	public Image() {
	}

	public Image(byte[] filename, @Nullable String title, @Nullable String description, LocalDate date, Boolean published, Album album) {
		this.filename = filename;
		this.title = title;
		this.description = description;
		this.date = date;
		this.published = published;
		this.album = album;
	}

	public Image(Long id, byte[] filename, @Nullable String title, @Nullable String description, LocalDate date, Boolean published, Album album) {
		this.id = id;
		this.filename = filename;
		this.title = title;
		this.description = description;
		this.date = date;
		this.published = published;
		this.album = album;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getFilename() {
		return filename;
	}

	public void setFilename(byte[] filename) {
		this.filename = filename;
	}

	@Nullable
	public String getTitle() {
		return title;
	}

	public void setTitle(@Nullable String title) {
		this.title = title;
	}

	@Nullable
	public String getDescription() {
		return description;
	}

	public void setDescription(@Nullable String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@Override
	public String toString() {
		return "Image{" +
				"id=" + id +
				", filename=" + Arrays.toString(filename) +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", date=" + date +
				", published=" + published +
				", album=" + album +
				'}';
	}
}
