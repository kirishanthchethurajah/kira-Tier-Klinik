package konglomerat.krankenhaus.kiratierklinik.repositories;

import konglomerat.krankenhaus.kiratierklinik.model.Besitzer;
import org.springframework.data.repository.CrudRepository;

public interface BesitzerRepository extends CrudRepository<Besitzer, Long> {
    Besitzer findByNachName(String nachName);
}
