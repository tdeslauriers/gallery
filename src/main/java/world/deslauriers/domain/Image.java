package world.deslauriers.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@MappedEntity(value = "image")
public class Image implements Serializable {

	private static final long serialVersionUID = 1431559123159076827L;
	
	@Id
	@GeneratedValue(value = Type.IDENTITY)
	private Long id;
	
	@NotBlank
	private String filename;
	private String title;
	private String description;
	
	@NotNull
	private LocalDate date;
	private Boolean published;
	
	@Relation(value = Kind.MANY_TO_ONE)
	@JoinTable(name = "album")
	private Album album;

	public Image(Long id, @NotBlank String filename, String title, String description, @NotNull LocalDate date,
			Boolean published) {
		this.id = id;
		this.filename = filename;
		this.title = title;
		this.description = description;
		this.date = date;
		this.published = published;
	}
}
