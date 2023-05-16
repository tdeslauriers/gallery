package world.deslauriers.repository;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import reactor.core.publisher.Flux;
import world.deslauriers.domain.AlbumImage;

@R2dbcRepository(dialect = Dialect.MYSQL)
public interface AlbumImageRepository extends ReactorCrudRepository<AlbumImage, Long> {

    @Join(value = "album", type = Join.Type.LEFT_FETCH)
    @Join(value = "image", type = Join.Type.LEFT_FETCH)
    Flux<AlbumImage> findAll();

    @Join(value = "album", type = Join.Type.LEFT_FETCH)
    @Join(value = "image", type = Join.Type.LEFT_FETCH)
    Flux<AlbumImage> findByImageId(Long id);
}
