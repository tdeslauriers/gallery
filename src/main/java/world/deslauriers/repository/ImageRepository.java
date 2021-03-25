package world.deslauriers.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import world.deslauriers.domain.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {

}
