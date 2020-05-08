package konglomerat.krankenhaus.kiratierklinik.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "besuch")
public class Besuch extends BaseEntity{
    @Column(name = "besuch_datum")
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private LocalDate lokaleDatum;
    private String beschreibung;

    @ManyToOne
    @JoinColumn(name = "tier_id")
    private Tier tier;

}
