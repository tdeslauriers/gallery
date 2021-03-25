package world.deslauriers.domain;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Introspected
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedEntity(value = "album")
public class Album implements Serializable{

	private static final long serialVersionUID = -7256115269410129232L;
	
	@Id
	@GeneratedValue(value = Type.IDENTITY)
	private Long id;
	
	@NotNull
	private String album;
	
	@Relation(value = Kind.ONE_TO_MANY)
	@JoinTable(name = "image")
	@JsonIgnore
	private Set<Image> images;
	
	public Album(@NotNull String album) {
		this.album = album;
	}

	public Album(Long id, @NotNull String album) {
		this.id = id;
		this.album = album;
	}

}
