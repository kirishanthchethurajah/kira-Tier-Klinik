package konglomerat.krankenhaus.kiratierklinik.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "fachgebiete")
public class FachGebiet extends BaseEntity{
    @Column(name = "beschreibung")
    private String beschreibung;

}
