package world.deslauriers.repository;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.Album;
import world.deslauriers.service.dto.ThumbnailDto;


@R2dbcRepository(dialect = Dialect.MYSQL)
public interface AlbumRepository extends ReactorCrudRepository<Album, Long> {

    // for menu (no join data)
    Flux<Album> findAllOrderByAlbumDesc();

    Mono<Album> findByAlbum(String album);

    @Query(value = """
            SELECT
                i.id,
                i.filename,
                i.title,
                i.description,
                i.date,
                i.published,
                i.thumbnail
            FROM album a 
                LEFT JOIN album_image ai ON a.id = ai.album_id
                LEFT JOIN image i ON ai.image_id = i.id
            WHERE 
                    a.album = :album
                AND
                    i.published = true
            ORDER BY i.date DESC
            """)
    Flux<ThumbnailDto> findThumbnailsByAlbum(String album);
}
