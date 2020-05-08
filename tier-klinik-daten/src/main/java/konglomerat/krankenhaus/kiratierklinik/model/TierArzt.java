package konglomerat.krankenhaus.kiratierklinik.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tier_arzt")
public class TierArzt extends Person {
    @Builder
    public TierArzt(Long id, String vorName, String nachName, String adresse, String stadt, String telefonnummer) {
        super(id, vorName, nachName, adresse, stadt, telefonnummer);
        this.fachgebiete = fachgebiete;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="tierarzt_fachgebiete",
            joinColumns = @JoinColumn(name= "tierarzt_id") ,
            inverseJoinColumns = @JoinColumn(name="fachgebiete_id"))
    private Set<FachGebiet> fachgebiete = new HashSet<>();


}
