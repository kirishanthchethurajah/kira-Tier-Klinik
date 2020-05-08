package konglomerat.krankenhaus.kiratierklinik.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tiere")
public class Tier extends BaseEntity{
    @Column(name = "name")
    private String Name;
    @ManyToOne
    @JoinColumn(name = "tiertyp_id")
    private TierTyp tierTyp;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Besitzer besitzer;
    @Column(name = "geburts_datum")
    private LocalDate geburtsDatum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tier")
    private Set<Besuch> besuch = new HashSet<>();

    public Set<Besuch> getBesuch() {
        return besuch;
    }

    public void setBesuch(Set<Besuch> besuch) {
        this.besuch = besuch;
    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public TierTyp getTierTyp() {
        return tierTyp;
    }

    public void setTierTyp(TierTyp tierTyp) {
        this.tierTyp = tierTyp;
    }

    public Besitzer getBesitzer() {
        return besitzer;
    }

    public void setBesitzer(Besitzer besitzer) {
        this.besitzer = besitzer;
    }

    public LocalDate getGeburtsDatum() {
        return geburtsDatum;
    }

    public void setGeburtsDatum(LocalDate geburtsDatum) {
        this.geburtsDatum = geburtsDatum;
    }
}
