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

    public Person(Long kId, String vorName, String nachName, String adresse, String stadt, String telefonnummer) {
        super(kId);
        this.adresse = adresse;
        this.nachName = nachName;
        this.telefonnummer = telefonnummer;
        this.vorName = vorName;
        this.stadt = stadt;
    }
//    @NotNull(message = "Vor Name darf nicht leer sein.")
    @Column(name = "vor_name")
    private String vorName;
//    @NotNull(message = "Nach Name darf nicht leer sein.")
    @Column(name = "nach_name")
    private String nachName;
//    @NotNull(message = "Person braucht einen Ort zum Leben.")
    @Column(name = "haus_adresse")
    private String adresse;
//    @NotNull(message = "Stadt kann nicht leer sein")
    @Column(name="stadt")
    private String stadt;
//    @NotNull(message = "Telefonnummer wird benötigt")
    @Column(name="telefon_nummer")
//    @Size(max = 10)
    private String telefonnummer;

}
