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

    // for menu (no join data)
    Iterable<Album> findAll();

    @Join(value = "albumImages", type = Join.Type.LEFT_FETCH)
    @Join(value = "albumImages.image", type = Join.Type.LEFT_FETCH)
    Optional<Album> findByAlbum(String album);
}
