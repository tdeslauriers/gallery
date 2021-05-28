package world.deslauriers.service.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

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

    public ImageDto() {
    }

    public ImageDto(Long id, String filename, String title, String description, LocalDate date, Boolean published) {
        this.id = id;
        this.filename = filename;
        this.title = title;
        this.description = description;
        this.date = date;
        this.published = published;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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

    @Override
    public String toString() {
        return "ImageDto{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", published=" + published +
                '}';
    }
}
