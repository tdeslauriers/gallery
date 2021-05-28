package world.deslauriers.repository;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import world.deslauriers.domain.Album;

import java.util.Optional;

@Repository
@JdbcRepository(dialect = Dialect.MYSQL)
public interface AlbumRepository extends CrudRepository<Album, Long> {

    @Join(value = "images", type = Join.Type.LEFT_FETCH)
    Iterable<Album> findAll();

    Optional<Album> findByAlbum(String album);
}
