package world.deslauriers.repository;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import world.deslauriers.domain.Album;
import world.deslauriers.domain.Image;

import java.util.List;

@Repository
@JdbcRepository(dialect = Dialect.MYSQL)
public interface ImageRepository extends CrudRepository<Image, Long> {

    // find all
    List<Image> findByPublished(Boolean published);

}
