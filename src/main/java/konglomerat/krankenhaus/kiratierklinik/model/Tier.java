package konglomerat.krankenhaus.kiratierklinik.model;

import java.time.LocalDate;

public class Tier {

  private TierTyp tierTyp;
  private Eigentümer eigentümer;
  private LocalDate geburtsDatum;

    public TierTyp getTierTyp() {
        return tierTyp;
    }

    public void setTierTyp(TierTyp tierTyp) {
        this.tierTyp = tierTyp;
    }

    public Eigentümer getEigentümer() {
        return eigentümer;
    }

    public void setEigentümer(Eigentümer eigentümer) {
        this.eigentümer = eigentümer;
    }

    public LocalDate getGeburtsDatum() {
        return geburtsDatum;
    }

    public void setGeburtsDatum(LocalDate geburtsDatum) {
        this.geburtsDatum = geburtsDatum;
    }
}
