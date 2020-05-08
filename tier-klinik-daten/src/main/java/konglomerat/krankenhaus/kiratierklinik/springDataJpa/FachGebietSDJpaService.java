package konglomerat.krankenhaus.kiratierklinik.springDataJpa;

import konglomerat.krankenhaus.kiratierklinik.model.FachGebiet;
import konglomerat.krankenhaus.kiratierklinik.repositories.FachGebietRepository;
import konglomerat.krankenhaus.kiratierklinik.service.FachGebietService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
@Profile("SpringDataJpa")
public class FachGebietSDJpaService implements FachGebietService {

    private FachGebietRepository fachGebietRepository;

    public FachGebietSDJpaService(FachGebietRepository fachGebietRepository) {
        this.fachGebietRepository = fachGebietRepository;
    }

    @Override
    public FachGebiet findById(Long aLong) {
        return fachGebietRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<FachGebiet> findAll() {
        Set<FachGebiet> fachGebiete = new HashSet<>();
        fachGebietRepository.findAll().forEach(fachGebiete::add);
        return fachGebiete;
    }

    @Override
    public FachGebiet save(FachGebiet object) {
        fachGebietRepository.save(object);
        return null;
    }

    @Override
    public void delete(FachGebiet object) {
        fachGebietRepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {
        fachGebietRepository.deleteById(aLong);

    }
}
