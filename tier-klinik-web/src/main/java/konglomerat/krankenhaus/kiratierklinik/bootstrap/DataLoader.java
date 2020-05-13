package konglomerat.krankenhaus.kiratierklinik.bootstrap;

import konglomerat.krankenhaus.kiratierklinik.model.*;
import konglomerat.krankenhaus.kiratierklinik.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner{

    private final TierArztService tierArztService;
    private final BesitzerService besitzerService;
    private final TierTypService tierTypService;
    private final FachGebietService fachGebietService;
    private final BesuchService besuchService;

    public DataLoader(TierArztService tierArztService, BesitzerService besitzerService, TierTypService tierTypService, FachGebietService fachGebietService, BesuchService besuchService) {
        this.tierArztService = tierArztService;
        this.besitzerService = besitzerService;
        this.tierTypService = tierTypService;
        this.fachGebietService = fachGebietService ;
        this.besuchService = besuchService;
    }

    @Override
    public void run(String... args) throws Exception {
//        if(tierArztService.findAll().size()==0) {
//            loadData();
//        }
        loadData();

    }

    private void loadData() {
        TierTyp tierTyp1 = new TierTyp();
        tierTyp1.setName("Hund");
        TierTyp savedTierTyp1 = tierTypService.save(tierTyp1);


        TierTyp tierTyp2 = new TierTyp();
        tierTyp2.setName("Katze");

        tierTypService.save(tierTyp2);


        Besitzer besitzer1= new Besitzer();
        besitzer1.setVorName("Steffen");
        besitzer1.setNachName("Planthaber");
        besitzer1.setAdresse("Friedrich-Engels-strasse, 8M");
        besitzer1.setTelefonnummer("4915414885469");
        besitzer1.setStadt("Hamburg");



        Tier tier1 = new Tier();
        tier1.setName("john");
        tier1.setTierTyp(savedTierTyp1);
        tier1.setBesitzer(besitzer1);
        tier1.setGeburtsDatum(LocalDate.ofEpochDay(23-07-2011));

        besitzer1.getTiere().add(tier1);

        besitzerService.save(besitzer1);

        Besuch besuch1 = new Besuch();
        besuch1.setBeschreibung("Feber");
        besuch1.setLokaleDatum(LocalDate.now());
        besuch1.setTier(tier1);

        besuchService.save(besuch1);



        FachGebiet fachGebiet1 = new FachGebiet();
        fachGebiet1.setBeschreibung("chirurg");
        FachGebiet savedFachGebiet1 = fachGebietService.save(fachGebiet1);

        TierArzt tierArzt1= new TierArzt();
        tierArzt1.setVorName("Anna");
        tierArzt1.setNachName("Foerster");
        tierArzt1.setAdresse("Bibliotherstrasse, 23");
        tierArzt1.setTelefonnummer("4915564805469");
        tierArzt1.setStadt("Bremen");


        tierArzt1.getFachgebiete().add(savedFachGebiet1);
        tierArztService.save(tierArzt1);
    }
}
