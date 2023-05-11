package world.deslauriers.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDate;
import java.util.Set;

@Serdeable
@MappedEntity
public class Image{

	@Id @GeneratedValue private Long id;
	private String filename; // string of UUID
	@Nullable private String title;
	@Nullable private String description;
	@JsonFormat(pattern="yyyy-MM-dd") @NonNull private LocalDate date;
	@NonNull private  Boolean published;
	private byte[] thumbnail;
	private byte[] presentation;
	@Nullable private byte[] image;

	@Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "image")
	private Set<AlbumImage> albumImages;

	public Image() {
	}

	public Image(Long id, @Nullable String title, @Nullable String description, @NonNull Boolean published) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.published = published;
	}

	public Image(String filename, @Nullable String title, @Nullable String description, @NonNull LocalDate date, @NonNull Boolean published, byte[] thumbnail, byte[] presentation, byte[] image) {
		this.filename = filename;
		this.title = title;
		this.description = description;
		this.date = date;
		this.published = published;
		this.thumbnail = thumbnail;
		this.presentation = presentation;
		this.image = image;
	}

	public Image(Long id, String filename, @Nullable String title, @Nullable String description, @NonNull LocalDate date, @NonNull Boolean published, byte[] thumbnail, byte[] presentation) {
		this.id = id;
		this.filename = filename;
		this.title = title;
		this.description = description;
		this.date = date;
		this.published = published;
		this.thumbnail = thumbnail;
		this.presentation = presentation;
	}

	public Image(Long id, String filename, @Nullable String title, @Nullable String description, @NonNull LocalDate date, @NonNull Boolean published, byte[] thumbnail, byte[] presentation, byte[] image, Set<AlbumImage> albumImages) {
		this.id = id;
		this.filename = filename;
		this.title = title;
		this.description = description;
		this.date = date;
		this.published = published;
		this.thumbnail = thumbnail;
		this.presentation = presentation;
		this.image = image;
		this.albumImages = albumImages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
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

	@NonNull
	public LocalDate getDate() {
		return date;
	}

	public void setDate(@NonNull LocalDate date) {
		this.date = date;
	}

	@NonNull
	public Boolean getPublished() {
		return published;
	}

	public void setPublished(@NonNull Boolean published) {
		this.published = published;
	}

	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	public byte[] getPresentation() {
		return presentation;
	}

	public void setPresentation(byte[] presentation) {
		this.presentation = presentation;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Set<AlbumImage> getAlbumImages() {
		return albumImages;
	}

	public void setAlbumImages(Set<AlbumImage> albumImages) {
		this.albumImages = albumImages;
	}
}
