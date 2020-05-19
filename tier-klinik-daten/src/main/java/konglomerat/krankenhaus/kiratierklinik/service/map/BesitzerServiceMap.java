package konglomerat.krankenhaus.kiratierklinik.service.map;


import konglomerat.krankenhaus.kiratierklinik.model.Besitzer;
import konglomerat.krankenhaus.kiratierklinik.model.Tier;
import konglomerat.krankenhaus.kiratierklinik.service.BesitzerService;
import konglomerat.krankenhaus.kiratierklinik.service.TierService;
import konglomerat.krankenhaus.kiratierklinik.service.TierTypService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile({"default",",map"})
public class BesitzerServiceMap extends AbstractServiceMap<Besitzer, Long> implements BesitzerService {

    private final TierService tierService;
    private final TierTypService tierTypService;

    public BesitzerServiceMap(TierService tierService, TierTypService tierTypService) {
        this.tierService = tierService;
        this.tierTypService = tierTypService;
    }

    @Override
    public Set<Besitzer> findAll() {
        return super.findAll();
    }

    @Override
    public Besitzer findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Besitzer object) {
        super.delete(object);
    }

    @Override
    public Besitzer save(Besitzer object) {
        if(object!=null) {
            if(object.getTiere() != null) {
                object.getTiere().forEach(tier -> {
                    if(tier.getTierTyp() != null){
                    if (tier.getTierTyp().getKId() == null) {
                        tier.setTierTyp(tierTypService.save(tier.getTierTyp()));
                    }} else {
                        throw new RuntimeException("Tier Typ ist nicht blank");
                    }

                    if (tier.getKId() == null) {
                        Tier tier1 = tierService.save(tier);
                        tier.setKId(tier1.getKId());
                    }
                });
            }
        }
        return super.save(object);
    }

    @Override
    public Besitzer findByNachName(String nachName) {

        return this.findAll()
                .stream()
                .filter(besitzer -> besitzer.getNachName().equalsIgnoreCase(nachName))
                .findFirst().
                orElse(null);

    }

    @Override
    public Set<Besitzer> findAllByNachNameLike(String nachName) {
        return this.findAll()
                .stream()
                .filter(besitzer -> besitzer.getNachName().contains(nachName)).collect(Collectors.toSet());
    }
}
