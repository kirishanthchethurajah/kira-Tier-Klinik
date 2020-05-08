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
    public Besitzer(Long id, String vorName, String nachName, String adresse, String stadt, String telefonnummer) {
        super(id, vorName, nachName, adresse, stadt, telefonnummer);
        this.tiere = tiere;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "besitzer")
    private Set<Tier> tiere = new HashSet<>();

}
