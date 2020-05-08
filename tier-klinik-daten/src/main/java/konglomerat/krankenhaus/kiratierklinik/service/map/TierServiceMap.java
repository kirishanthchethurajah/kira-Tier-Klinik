package konglomerat.krankenhaus.kiratierklinik.service.map;

import konglomerat.krankenhaus.kiratierklinik.model.Tier;
import konglomerat.krankenhaus.kiratierklinik.service.TierService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default",",map"})
public class TierServiceMap extends AbstractServiceMap<Tier,Long> implements TierService{

    @Override
    public Tier findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Tier> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public void delete(Tier object) {
        super.delete(object);

    }

    @Override
    public Tier save(Tier object) {
        return super.save(object);
    }
}
