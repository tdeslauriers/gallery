package world.deslauriers.repository;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import world.deslauriers.domain.Image;
import world.deslauriers.repository.dto.ImageDto;
import world.deslauriers.service.dto.FullResolutionDto;
import world.deslauriers.service.dto.ThumbnailDto;

import java.util.Optional;

@Repository
@JdbcRepository(dialect = Dialect.MYSQL)
public interface ImageRepository extends CrudRepository<Image, Long> {

    @Join(value = "albumImages", type = Join.Type.LEFT_FETCH)
    @Join(value = "albumImages.album", type = Join.Type.LEFT_FETCH)
    Optional<Image> findById(Long id);

    @Query("""
            SELECT
                i.id,
                i.filename,
                i.title,
                i.description,
                i.date,
                i.published,
                i.thumbnail,
                i.presentation
            FROM image i
            WHERE i.filename = :filename
            """)
    Optional<ImageDto> findByFilename(String filename);

    @Query(value = """
            UPDATE image i SET
                i.title = :title,
                i.description = :description,
                i.published = :published
            WHERE i.id = :id
            """)
    void updateImage(Long id, String title, String description, Boolean published);

    @Query(value = """
            SELECT
                i.id,
                i.filename,
                i.title,
                i.description,
                i.date,
                i.published,
                i.thumbnail
            FROM image i
            WHERE i.published = false
            """)
    Iterable<ThumbnailDto> findAllUnpublished();

    @Join(value = "albumImages", type = Join.Type.LEFT_FETCH)
    @Join(value = "albumImages.album", type = Join.Type.LEFT_FETCH)
    Iterable<Image> findAll();

    @Query(value = """
            SELECT
                i.filename
            FROM image i""")
    Iterable<String> findAllImageFilenames();

    @Query("""
            SELECT
                i.filename,
                i.image
            FROM image i
            WHERE i.filename = :filename
            """)
    Optional<FullResolutionDto> findFullResolutionByFilename(String filename);

    @Query("""
            SELECT
                i.id
            FROM image i
            """)
    Iterable<Long> findAllImageIds();
}
