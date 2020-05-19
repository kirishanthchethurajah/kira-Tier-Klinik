package konglomerat.krankenhaus.kiratierklinik.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="besitzer")
public class Besitzer extends Person {
    @Builder
    public Besitzer(Long kId, String vorName, String nachName, String adresse, String stadt, String telefonnummer, Set<Tier> tiere) {
        super(kId, vorName, nachName, adresse, stadt, telefonnummer);
        if(tiere != null) {
            this.tiere = tiere;
        }
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "besitzer")
    private Set<Tier> tiere = new HashSet<>();

    public Tier getTier(String name){
        return getTier(name, false);
    }

    public Tier getTier(String name, boolean isNeu){
        for(Tier tier: tiere) {
            if(!isNeu || !tier.isNew()) {
                String comp = tier.getName();
                if(comp.equalsIgnoreCase(name.toLowerCase())){
                    return  tier;
                }
            }
        }
        return  null;
    }

}
