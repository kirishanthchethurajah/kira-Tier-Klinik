package konglomerat.krankenhaus.kiratierklinik.repositories;

import konglomerat.krankenhaus.kiratierklinik.model.Besitzer;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface BesitzerRepository extends CrudRepository<Besitzer, Long> {
    Besitzer findByNachName(String nachName);

    Set<Besitzer> findByNachNameLike(String nachName);
}
