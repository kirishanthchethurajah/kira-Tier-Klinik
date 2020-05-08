package konglomerat.krankenhaus.kiratierklinik.springDataJpa;

import konglomerat.krankenhaus.kiratierklinik.model.TierTyp;
import konglomerat.krankenhaus.kiratierklinik.repositories.TierTypRepository;
import konglomerat.krankenhaus.kiratierklinik.service.TierTypService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
@Profile("SpringDataJpa")
public class TierTypSDJpaService implements TierTypService {

    private TierTypRepository tierTypRepository;

    public TierTypSDJpaService(TierTypRepository tierTypRepository) {
        this.tierTypRepository = tierTypRepository;
    }

    @Override
    public TierTyp findById(Long aLong) {
        return tierTypRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<TierTyp> findAll() {
        Set<TierTyp> tierTyp = new HashSet<>();
        tierTypRepository.findAll().forEach(tierTyp::add);
        return tierTyp;
    }

    @Override
    public TierTyp save(TierTyp object) {
        return tierTypRepository.save(object);
    }

    @Override
    public void delete(TierTyp object) {
        tierTypRepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {
        tierTypRepository.deleteById(aLong);

    }
}
