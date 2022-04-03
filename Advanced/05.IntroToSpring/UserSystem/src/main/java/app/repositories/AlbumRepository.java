package app.repositories;

import app.models.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {
}
