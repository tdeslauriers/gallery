package world.deslauriers.service.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Introspected
public class ImageDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -7489172596709934112L;

    private Long id;
    private String filename;
    private String title;
    private String description;
    private LocalDate date;
    private Boolean published;
}
