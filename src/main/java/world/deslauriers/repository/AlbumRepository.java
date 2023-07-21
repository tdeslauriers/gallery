package world.deslauriers.repository;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import world.deslauriers.domain.Album;

@R2dbcRepository(dialect = Dialect.MYSQL)
public interface AlbumRepository extends ReactorCrudRepository<Album, Long> {

    Flux<Album> findAllOrderByAlbumDesc();

    // for menu (no join data)
    @Query("""
            SELECT DISTINCT
              a.id,
              a.album,
              a.description
            FROM album a
              LEFT JOIN album_image ai ON a.id = ai.album_id
            WHERE ai.image_id IS NOT NULL
            ORDER BY a.album DESC
            """)
    Flux<Album> findPopulated();

    Mono<Album> findByAlbum(String album);

    @Query(value = "INSERT INTO album (id, album, description) VALUES (:id, :album, :description)", nativeQuery = true)
    Mono<Void> saveRestoreAlbum(Long id, String album, String description);

    Mono<Boolean> existsByAlbum(String album);
}
