package konglomerat.krankenhaus.kiratierklinik.service.map;

import konglomerat.krankenhaus.kiratierklinik.model.FachGebiet;
import konglomerat.krankenhaus.kiratierklinik.service.FachGebietService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@Profile({"default",",map"})
public class FachGebietServiceMap extends AbstractServiceMap<FachGebiet,Long> implements FachGebietService {

    @Override
    public FachGebiet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<FachGebiet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
       super.deleteById(id);
    }

    @Override
    public void delete(FachGebiet object) {
      super.delete(object);
    }

    @Override
    public FachGebiet save(FachGebiet object) {
        return super.save(object);
    }
}
