package konglomerat.krankenhaus.kiratierklinik.springDataJpa;

import konglomerat.krankenhaus.kiratierklinik.model.Besuch;
import konglomerat.krankenhaus.kiratierklinik.repositories.BesuchRepository;
import konglomerat.krankenhaus.kiratierklinik.service.BesuchService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
@Profile("SpringDataJpa")
public class BesuchSDJpaService implements BesuchService {

    private BesuchRepository besuchRepository;

    public BesuchSDJpaService(BesuchRepository besuchRepository) {
        this.besuchRepository = besuchRepository;
    }

    @Override
    public Besuch findById(Long aLong) {
        return besuchRepository.findById(aLong).orElse(null) ;
    }

    @Override
    public Set<Besuch> findAll() {
        Set<Besuch> besuch = new HashSet<>();
        besuchRepository.findAll().forEach(besuch::add);
        return besuch;
    }

    @Override
    public Besuch save(Besuch object) {
        return besuchRepository.save(object);
    }

    @Override
    public void delete(Besuch object) {
        besuchRepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {
        besuchRepository.deleteById(aLong);

    }
}
