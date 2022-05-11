package world.deslauriers.repository;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;
import world.deslauriers.domain.AlbumImage;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface AlbumImageRepository extends PageableRepository<AlbumImage, Long> {

    @Join(value = "album", type = Join.Type.LEFT_FETCH)
    @Join(value = "image", type = Join.Type.LEFT_FETCH)
    Iterable<AlbumImage> findAll();
}
