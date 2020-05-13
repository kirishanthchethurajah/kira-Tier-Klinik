package konglomerat.krankenhaus.kiratierklinik.formatters;

import konglomerat.krankenhaus.kiratierklinik.model.TierTyp;
import konglomerat.krankenhaus.kiratierklinik.service.TierTypService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class TierTypFormatter implements Formatter<TierTyp> {
    private final TierTypService tierTypService;

    public TierTypFormatter(TierTypService tierTypService) {
        this.tierTypService = tierTypService;
    }


    @Override
    public TierTyp parse(String s, Locale locale) throws ParseException {
        Collection<TierTyp> tierTyps = tierTypService.findAll();
        for(TierTyp tierTyp: tierTyps){
            if(tierTyp.getName().equals(s)){
                return tierTyp;
            }

        }
        return null;
    }

    @Override
    public String print(TierTyp tierTyp, Locale locale) {
        return tierTyp.getName();
    }
}
