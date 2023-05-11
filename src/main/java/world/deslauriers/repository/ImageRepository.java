package world.deslauriers.repository;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.Image;
import world.deslauriers.repository.dto.ImageDto;
import world.deslauriers.service.dto.FullResolutionDto;
import world.deslauriers.service.dto.ThumbnailDto;

@R2dbcRepository(dialect = Dialect.MYSQL)
public interface ImageRepository extends ReactorCrudRepository<Image, Long> {

    @Join(value = "albumImages", type = Join.Type.LEFT_FETCH)
    @Join(value = "albumImages.album", type = Join.Type.LEFT_FETCH)
    Mono<Image> findById(Long id);

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
    Mono<ImageDto> findByFilename(String filename);

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
    Flux<ThumbnailDto> findAllUnpublished();

    @Join(value = "albumImages", type = Join.Type.LEFT_FETCH)
    @Join(value = "albumImages.album", type = Join.Type.LEFT_FETCH)
    Flux<Image> findAll();

    @Query(value = """
            SELECT
                i.filename
            FROM image i""")
    Flux<String> findAllImageFilenames();

    @Query("""
            SELECT
                i.filename,
                i.image
            FROM image i
            WHERE i.filename = :filename
            """)
    Mono<FullResolutionDto> findFullResolutionByFilename(String filename);

    @Query("""
            SELECT
                i.id
            FROM image i
            """)
    Flux<Long> findAllImageIds();
}
