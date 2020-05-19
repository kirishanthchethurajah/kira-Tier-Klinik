package konglomerat.krankenhaus.kiratierklinik.service.map;

import konglomerat.krankenhaus.kiratierklinik.model.FachGebiet;
import konglomerat.krankenhaus.kiratierklinik.model.TierArzt;
import konglomerat.krankenhaus.kiratierklinik.service.FachGebietService;
import konglomerat.krankenhaus.kiratierklinik.service.TierArztService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default",",map"})
public class TierArztServiceMap extends AbstractServiceMap<TierArzt, Long> implements TierArztService {

    private final FachGebietService fachGebietService;

    public TierArztServiceMap(FachGebietService fachGebietService) {
        this.fachGebietService = fachGebietService;
    }

    @Override
    public TierArzt findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<TierArzt> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
       super.deleteById(id);
    }

    @Override
    public void delete(TierArzt object) {
      super.delete(object);
    }

    @Override
    public TierArzt save(TierArzt object) {
        if(object.getFachgebiete().size()>0){
            object.getFachgebiete().forEach(fachGebiet -> {
                if(fachGebiet.getKId() == null){
                    FachGebiet savedFachGebiet = fachGebietService.save(fachGebiet);
                    fachGebiet.setKId(savedFachGebiet.getKId());
                }
            });

        }

        return super.save(object);
    }
}
