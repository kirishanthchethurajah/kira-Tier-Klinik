package konglomerat.krankenhaus.kiratierklinik.model;

import java.time.LocalDate;

public class Tier {

  private TierTyp tierTyp;
  private Besitzer besitzer;
  private LocalDate geburtsDatum;

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
