package konglomerat.krankenhaus.kiratierklinik.springDataJpa;

import konglomerat.krankenhaus.kiratierklinik.model.TierArzt;
import konglomerat.krankenhaus.kiratierklinik.repositories.TierArztRepository;
import konglomerat.krankenhaus.kiratierklinik.service.TierArztService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("SpringDataJpa")
public class TierArztSDJpaService implements TierArztService {

    private TierArztRepository tierArztRepository;

    public TierArztSDJpaService(TierArztRepository tierArztRepository) {
        this.tierArztRepository = tierArztRepository;
    }


    @Override
    public TierArzt findById(Long aLong) {
        return  tierArztRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<TierArzt> findAll() {
        Set<TierArzt> arzt= new HashSet<>();
        tierArztRepository.findAll().forEach(arzt::add);
        return arzt;
    }

    @Override
    public TierArzt save(TierArzt object) {
        return tierArztRepository.save(object) ;
    }

    @Override
    public void delete(TierArzt object) {
        tierArztRepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {
        tierArztRepository.deleteById(aLong);

    }
}
