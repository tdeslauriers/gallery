package world.deslauriers.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.GeneratedValue.Type;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.data.annotation.Relation.Kind;
import io.micronaut.data.jdbc.annotation.JoinTable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Introspected
@MappedEntity(value = "album")
public class Album implements Serializable{

	private static final long serialVersionUID = -7256115269410129232L;
	
	@Id
	@GeneratedValue(value = Type.IDENTITY)
	private Long id;
	
	@NotNull
	private String album;
	
	@Relation(value = Kind.ONE_TO_MANY, mappedBy = "album")
	@JoinTable(name = "image")
	@JsonIgnore
	private Set<Image> images;

	public Album() {
	}

	public Album(@NotNull String album) {
		this.album = album;
	}

	public Album(Long id, @NotNull String album) {
		this.id = id;
		this.album = album;
	}

	public Album(Long id, String album, Set<Image> images) {
		this.id = id;
		this.album = album;
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Album{" +
				"id=" + id +
				", album='" + album + '\'' +
				", images=" + images +
				'}';
	}
}
