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
@Entity
@Table(name = "tiere")
public class Tier extends BaseEntity{
    @Builder
    public Tier(Long id, String name, TierTyp tierTyp,  Besitzer besitzer,LocalDate geburtsDatum, Set<Besuch> besuch) {
        super(id);
        this.name = name;
        this.tierTyp = tierTyp;
        this.besitzer = besitzer;
        this.geburtsDatum = geburtsDatum;
        if(besuch == null || besuch.size()>0 ) {
            this.besuch = besuch;
        }
    }

    @Column(name = "name")
    private String name;
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




}




