package konglomerat.krankenhaus.kiratierklinik.controller;

import konglomerat.krankenhaus.kiratierklinik.model.Besitzer;
import konglomerat.krankenhaus.kiratierklinik.model.Tier;
import konglomerat.krankenhaus.kiratierklinik.model.TierTyp;
import konglomerat.krankenhaus.kiratierklinik.service.BesitzerService;
import konglomerat.krankenhaus.kiratierklinik.service.TierService;
import konglomerat.krankenhaus.kiratierklinik.service.TierTypService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class TierControllerTest {

    @Mock
    TierService tierService;

    @Mock
    BesitzerService besitzerService;

    @Mock
    TierTypService tierTypService;

    @InjectMocks
    TierController tierController;

    MockMvc mockMvc;
    Besitzer besitzer;
    Set<TierTyp> tierTypSet;

    @BeforeEach
    void setUp() {
        besitzer = Besitzer.builder().id(1L).vorName("Christopher").nachName("Nolan").build();

        tierTypSet = new HashSet<>();
        tierTypSet.add(TierTyp.builder().id(1L).name("Hund").build());
        tierTypSet.add(TierTyp.builder().id(2L).name("Katze").build());

        mockMvc = MockMvcBuilders.standaloneSetup(tierController).build();
    }

    @Test
    void erstellenFormular() throws Exception{
        when(besitzerService.findById(anyLong())).thenReturn(besitzer);
        when(tierTypService.findAll()).thenReturn(tierTypSet);

        mockMvc.perform(get("/besitzer/1/tier/neu"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("besitzer"))
                .andExpect(model().attributeExists("tier"))
                .andExpect(view().name("tierFormularErstellenOderAktualisieren"));

    }

    @Test
    void prozessErstellenFormular() throws Exception{
        when(besitzerService.findById(anyLong())).thenReturn(besitzer);
        when(tierTypService.findAll()).thenReturn(tierTypSet);

        mockMvc.perform(post("/besitzer/1/tier/neu"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/besitzer/1"));

        verify(tierService).save(any());

    }

    @Test
    void aktualisierenFormular() throws Exception{
        when(besitzerService.findById(anyLong())).thenReturn(besitzer);
        when(tierTypService.findAll()).thenReturn(tierTypSet);
        when(tierService.findById(anyLong())).thenReturn(Tier.builder().id(1L).build());

        mockMvc.perform(get("/besitzer/1/tier/1/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("besitzer"))
                .andExpect(model().attributeExists("tier"))
                .andExpect(view().name("tierFormularErstellenOderAktualisieren"));

    }

    @Test
    void prozessAktualisierenFormular() throws Exception{
        when(besitzerService.findById(anyLong())).thenReturn(besitzer);
        when(tierTypService.findAll()).thenReturn(tierTypSet);

        mockMvc.perform(post("/besitzer/1/tier/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/besitzer/1"));

        verify(tierService).save(any());
    }



}