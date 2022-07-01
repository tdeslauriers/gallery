package world.deslauriers.repository;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import world.deslauriers.domain.Album;
import world.deslauriers.service.dto.ThumbnailDto;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
@JdbcRepository(dialect = Dialect.MYSQL)
public interface AlbumRepository extends CrudRepository<Album, Long> {

    // for menu (no join data)
    Iterable<Album> findAll();

    Optional<Album> findByAlbum(String album);

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
            WHERE a.album = :album
            ORDER BY i.date
            """)
    HashSet<ThumbnailDto> findThumbnailsByAlbum(String album);
}
