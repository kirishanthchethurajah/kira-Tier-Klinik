package konglomerat.krankenhaus.kiratierklinik.service.map;

import konglomerat.krankenhaus.kiratierklinik.model.Besuch;
import konglomerat.krankenhaus.kiratierklinik.service.BesuchService;
import konglomerat.krankenhaus.kiratierklinik.service.TierService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@Profile({"default",",map"})
public class BesuchServiceMap extends AbstractServiceMap<Besuch, Long> implements BesuchService {

    private final TierService tierService;

    public BesuchServiceMap(TierService tierService) {
        this.tierService = tierService;
    }

    @Override
    public Besuch findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Besuch> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
       super.deleteById(id);
    }

    @Override
    public void delete(Besuch object) {
        super.delete(object);
    }

    @Override
    public Besuch save(Besuch object) {
        if(object.getTier() == null || object.getTier().getBesitzer() == null ||
                object.getTier().getKId() == null || object.getTier().getBesitzer().getKId() == null)
        {
            throw new RuntimeException("Besuch ist nicht gultig");
        }
        return super.save(object);
    }
}
