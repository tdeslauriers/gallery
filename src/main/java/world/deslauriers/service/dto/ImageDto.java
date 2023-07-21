package world.deslauriers.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import world.deslauriers.domain.Album;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Serdeable
public class ImageDto {

    private Long id;
    private String filename; // string of UUID
    @Nullable
    private String title;
    @Nullable
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd")
    @NonNull
    private LocalDate date;
    @NonNull
    private Boolean published;

    // moved up above long image byte array values for ease of reading in json
    private Set<Album> albums = new HashSet<>();
    private byte[] thumbnail;
    private byte[] presentation;


    public ImageDto() {
    }

    public ImageDto(Long id, String filename, @Nullable String title, @Nullable String description, @NonNull LocalDate date, @NonNull Boolean published, byte[] thumbnail, byte[] presentation) {
        this.id = id;
        this.filename = filename;
        this.title = title;
        this.description = description;
        this.date = date;
        this.published = published;
        this.thumbnail = thumbnail;
        this.presentation = presentation;
    }

    public ImageDto(Long id, String filename, @Nullable String title, @Nullable String description, @NonNull LocalDate date, @NonNull Boolean published, byte[] thumbnail, byte[] presentation, Set<Album> albums) {
        this.id = id;
        this.filename = filename;
        this.title = title;
        this.description = description;
        this.date = date;
        this.published = published;
        this.thumbnail = thumbnail;
        this.presentation = presentation;
        this.albums = albums;
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

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
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
                ", thumbnail=" + Arrays.toString(thumbnail) +
                ", presentation=" + Arrays.toString(presentation) +
                ", albums=" + albums +
                '}';
    }
}
