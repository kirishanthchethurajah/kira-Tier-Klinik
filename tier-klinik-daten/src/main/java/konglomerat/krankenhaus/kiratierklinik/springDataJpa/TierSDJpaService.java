package konglomerat.krankenhaus.kiratierklinik.springDataJpa;

import konglomerat.krankenhaus.kiratierklinik.model.Tier;
import konglomerat.krankenhaus.kiratierklinik.repositories.TierRepository;
import konglomerat.krankenhaus.kiratierklinik.service.TierService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
@Profile("SpringDataJpa")
public class TierSDJpaService implements TierService {

    private TierRepository tierRepository;

    public TierSDJpaService(TierRepository tierRepository) {
        this.tierRepository = tierRepository;
    }

    @Override
    public Tier findById(Long aLong) {
        return tierRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<Tier> findAll() {
        Set<Tier> tiere = new HashSet<>();
        tierRepository.findAll().forEach(tiere::add);
        return tiere;
    }

    @Override
    public Tier save(Tier object) {
        return tierRepository.save(object);
    }

    @Override
    public void delete(Tier object) {
        tierRepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {
        tierRepository.deleteById(aLong);

    }
}
