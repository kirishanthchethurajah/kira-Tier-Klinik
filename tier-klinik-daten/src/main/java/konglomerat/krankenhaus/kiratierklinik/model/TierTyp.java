package konglomerat.krankenhaus.kiratierklinik.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tier_typ")
public class TierTyp extends BaseEntity{

    @Builder
    public TierTyp(Long id, String name){
        super(id);
        this.name=name;
    }

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
