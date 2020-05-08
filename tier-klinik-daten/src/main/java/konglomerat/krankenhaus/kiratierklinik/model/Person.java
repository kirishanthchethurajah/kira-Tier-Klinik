package konglomerat.krankenhaus.kiratierklinik.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity{

    public Person(Long id, String vorName, String nachName, String adresse, String stadt, String telefonnummer) {
        super(id);
        this.adresse = adresse;
        this.nachName = nachName;
        this.telefonnummer = telefonnummer;
        this.vorName = vorName;
        this.stadt = stadt;
    }

    @Column(name = "vor_name")
    private String vorName;
    @Column(name = "nach_name")
    private String nachName;
    @Column(name = "haus_adresse")
    private String adresse;
    @Column(name="stadt")
    private String stadt;
    @Column(name="telefon_nummer")
    private String telefonnummer;

}
