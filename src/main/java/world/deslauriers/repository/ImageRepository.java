package world.deslauriers.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import world.deslauriers.domain.Image;

import java.util.Optional;

@Repository
@JdbcRepository(dialect = Dialect.MYSQL)
public interface ImageRepository extends CrudRepository<Image, Long> {

    Optional<Image> findByFilenameAndPublishedTrue(String filename);
}
