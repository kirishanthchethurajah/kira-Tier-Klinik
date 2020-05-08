package konglomerat.krankenhaus.kiratierklinik.springDataJpa;

import konglomerat.krankenhaus.kiratierklinik.model.Besitzer;
import konglomerat.krankenhaus.kiratierklinik.repositories.BesitzerRepository;
import konglomerat.krankenhaus.kiratierklinik.repositories.TierRepository;
import konglomerat.krankenhaus.kiratierklinik.repositories.TierTypRepository;
import konglomerat.krankenhaus.kiratierklinik.service.BesitzerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
@Profile("SpringDataJpa")
public class BesitzerSDJpaService implements BesitzerService {

    private BesitzerRepository besitzerRepository;
    private TierTypRepository tierTypRepository;
    private TierRepository tierRepository;

    public BesitzerSDJpaService(BesitzerRepository besitzerRepository, TierTypRepository tierTypRepository, TierRepository tierRepository) {
        this.besitzerRepository = besitzerRepository;
        this.tierTypRepository = tierTypRepository;
        this.tierRepository = tierRepository;
    }

    @Override
    public Besitzer findByNachName(String nachName) {
        return besitzerRepository.findByNachName(nachName);
    }

    @Override
    public Besitzer findById(Long aLong) {
        return besitzerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<Besitzer> findAll() {
        Set<Besitzer> besitzer = new HashSet<>();
        besitzerRepository.findAll().forEach(besitzer::add);
        return besitzer;
    }

    @Override
    public Besitzer save(Besitzer object) {
        return besitzerRepository.save(object);
    }

    @Override
    public void delete(Besitzer object) {
        besitzerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        besitzerRepository.deleteById(aLong);

    }
}
