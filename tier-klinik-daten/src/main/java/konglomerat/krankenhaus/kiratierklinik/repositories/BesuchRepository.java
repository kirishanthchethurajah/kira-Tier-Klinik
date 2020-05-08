package konglomerat.krankenhaus.kiratierklinik.repositories;

import konglomerat.krankenhaus.kiratierklinik.model.Besuch;
import org.springframework.data.repository.CrudRepository;

public interface BesuchRepository extends CrudRepository<Besuch, Long> {
}
