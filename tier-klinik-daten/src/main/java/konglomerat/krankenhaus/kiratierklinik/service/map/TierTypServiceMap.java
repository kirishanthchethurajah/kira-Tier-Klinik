package konglomerat.krankenhaus.kiratierklinik.service.map;

import konglomerat.krankenhaus.kiratierklinik.model.TierTyp;
import konglomerat.krankenhaus.kiratierklinik.service.TierTypService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@Profile({"default",",map"})
public class TierTypServiceMap extends AbstractServiceMap<TierTyp, Long> implements TierTypService {
    @Override
    public TierTyp findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<TierTyp> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public void delete(TierTyp object) {
        super.delete(object);

    }

    @Override
    public TierTyp save(TierTyp object) {
        return super.save(object);
    }
}
