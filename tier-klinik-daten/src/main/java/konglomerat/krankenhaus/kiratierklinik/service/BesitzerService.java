package konglomerat.krankenhaus.kiratierklinik.service;

import konglomerat.krankenhaus.kiratierklinik.model.Besitzer;


public interface BesitzerService extends BaseCrudService<Besitzer,Long> {

    Besitzer findByNachName(String nachName);

}
