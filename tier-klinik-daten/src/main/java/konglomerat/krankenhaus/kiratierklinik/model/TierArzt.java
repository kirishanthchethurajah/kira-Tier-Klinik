package konglomerat.krankenhaus.kiratierklinik.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tierarzt")
public class TierArzt extends Person {


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tierarzt_fachgebiete",
            joinColumns = @JoinColumn(name = "tierarzt_id") ,
            inverseJoinColumns = @JoinColumn(name ="fachgebiet_id"))
    private Set<FachGebiet> fachgebiete = new HashSet<>();


}
