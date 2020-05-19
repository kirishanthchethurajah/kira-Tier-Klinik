package konglomerat.krankenhaus.kiratierklinik.controller;

import konglomerat.krankenhaus.kiratierklinik.model.Besitzer;
import konglomerat.krankenhaus.kiratierklinik.model.Tier;
import konglomerat.krankenhaus.kiratierklinik.model.TierTyp;
import konglomerat.krankenhaus.kiratierklinik.service.BesuchService;
import konglomerat.krankenhaus.kiratierklinik.service.TierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BesuchControllerTest {
    @Mock
    TierService tierService;

    @Mock
    BesuchService besuchService;

    @InjectMocks
    BesuchController besuchController;

    private MockMvc mockMvc;

    private final UriTemplate besuchUriTemplate = new UriTemplate("/besitzer/{besitzerId}/tier/{tierId}/besuch/neu");
    private final Map<String,String> uriMap = new HashMap<>();
    private URI besuchUri;

    @BeforeEach
    void setUp() {
        Long Id = 1L;
        when(tierService.findById(anyLong()))
                .thenReturn(Tier.builder().kId(Id).geburtsDatum(LocalDate.of(2010,11,20)).name("Lucifer")
                        .besuch(new HashSet<>()).besitzer(Besitzer.builder().kId(Id).vorName("Dwayne").nachName("Johnson").build())
                        .tierTyp(TierTyp.builder().name("Katze").build()).build());

        uriMap.clear();
        uriMap.put("besitzerId",Id.toString());
        uriMap.put("tierId", Id.toString());
        besuchUri = besuchUriTemplate.expand(uriMap);

        mockMvc = MockMvcBuilders.standaloneSetup(besuchController).build();

    }





    @Test
    void initNeuBesuchFormular() throws Exception {
        mockMvc.perform(get(besuchUri))
                .andExpect(status().isOk())
                .andExpect(view().name("besuchFormularErstellenOderAktualisieren"));
    }

    @Test
    void prozessInitNeuBesuchFormular() throws Exception {
        mockMvc.perform(post(besuchUri)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("besuchDatum","2020-18-05")
                        .param("beschreibung", "Feber"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/besitzer/{besitzerId}"))
                .andExpect(model().attributeExists("besuch"));
    }
}