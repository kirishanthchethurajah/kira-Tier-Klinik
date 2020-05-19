package konglomerat.krankenhaus.kiratierklinik.controller;

import konglomerat.krankenhaus.kiratierklinik.model.Besitzer;
import konglomerat.krankenhaus.kiratierklinik.service.BesitzerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class BesitzerControllerTest {
    @Mock
    BesitzerService besitzerService;
    @InjectMocks
    BesitzerController besitzerController;

    MockMvc mockMvc;

    Set<Besitzer> besitzerSet;
    @BeforeEach
    void setup(){
      besitzerSet= new HashSet<>();
      besitzerSet.add(Besitzer.builder().kId(1L).vorName("Christopher").nachName("Nolan").build());
      besitzerSet.add(Besitzer.builder().kId(2L).vorName("Mathew").nachName("klaus").build());
      mockMvc = MockMvcBuilders.standaloneSetup(besitzerController).build();
    }


    @Test
    void anzeigeBesitzer() throws Exception {
        when(besitzerService.findById(anyLong())).thenReturn(Besitzer.builder().kId(143L).vorName("Angelo").nachName("Mathews").adresse("Hbf").build());
        mockMvc.perform(MockMvcRequestBuilders.get("/besitzer/143"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("besitzer/besitzerAngaben"))
                .andExpect(model().attribute("besitzer",hasProperty("vorName", Matchers.is("Angelo"))));
    }

    @Test
    void prozessFormFindenEins() throws Exception {
        when(besitzerService.findAllByNachNameLike(anyString())).thenReturn((besitzerSet));
        besitzerSet.removeIf(besitzer -> besitzer.getKId()==2l);
        mockMvc.perform(MockMvcRequestBuilders.get("/besitzer"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(view().name("redirect:/besitzer/1"));
    }


    @Test
    void prozessFormFindenViele() throws Exception {
        when(besitzerService.findAllByNachNameLike(anyString())).thenReturn((besitzerSet));

        mockMvc.perform(MockMvcRequestBuilders.get("/besitzer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("besitzer/besitzerList"))
                .andExpect(model().attribute("besitzer",hasSize(2)));

    }

    @Test
    void erstellenBesitzer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/besitzer/neu"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("besitzer/besitzerFormularErstellenOderAktualisieren"))
                .andExpect(model().attributeExists("besitzer"));
        verifyNoInteractions(besitzerService);
    }

    @Test
    void prozessErstellenBesitzer() throws Exception{
        when(besitzerService.save(ArgumentMatchers.any())).thenReturn(Besitzer.builder().kId(123L).vorName("Tom").nachName("Cruise").adresse("HafenStrasse").build());
        mockMvc.perform(MockMvcRequestBuilders.post("/besitzer/neu"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(view().name("redirect:/besitzer/123"))
                .andExpect(model().attributeExists("besitzer"));

        verify(besitzerService).save(ArgumentMatchers.any());
    }

    @Test
    void bearbeitenBesitzer() throws Exception {
        when(besitzerService.findById(anyLong())).thenReturn(Besitzer.builder().kId(123L).vorName("Tom").nachName("Cruise").adresse("HafenStrasse").build());

        mockMvc.perform(MockMvcRequestBuilders.get("/besitzer/123/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("besitzer/besitzerFormularErstellenOderAktualisieren"))
                .andExpect(model().attributeExists("besitzer"));

    }


    @Test
    void prozessBearbeitenBesitzer() throws Exception {
        when(besitzerService.save(ArgumentMatchers.any())).thenReturn(Besitzer.builder().kId(123L).vorName("Tom").nachName("Cruise").adresse("HafenStrasse").build());
        mockMvc.perform(MockMvcRequestBuilders.post("/besitzer/123/edit"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(view().name("redirect:/besitzer/123"))
                .andExpect(model().attributeExists("besitzer"));

        verify(besitzerService).save(ArgumentMatchers.any());
    }

    @Test
    void prozessFormFinden() throws Exception {
        when(besitzerService.findAllByNachNameLike("%%"))
                .thenReturn(besitzerSet);

        mockMvc.perform(MockMvcRequestBuilders.get("/besitzer/").param("nachName",""))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(view().name("besitzer/besitzerList"))
        .andExpect(model().attribute("besitzer",hasSize(2)));
    }


}