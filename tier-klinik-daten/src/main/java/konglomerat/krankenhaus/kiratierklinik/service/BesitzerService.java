package konglomerat.krankenhaus.kiratierklinik.service;

import konglomerat.krankenhaus.kiratierklinik.model.Besitzer;

import java.util.Set;


public interface BesitzerService extends BaseCrudService<Besitzer,Long> {

    Besitzer findByNachName(String nachName);

    Set<Besitzer> findAllByNachNameLike(String nachName);

}
