package konglomerat.krankenhaus.kiratierklinik.controller;

import konglomerat.krankenhaus.kiratierklinik.model.Besitzer;
import konglomerat.krankenhaus.kiratierklinik.service.BesitzerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
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
      besitzerSet.add(Besitzer.builder().id(1L).vorName("Christopher").nachName("Nolan").build());
      besitzerSet.add(Besitzer.builder().id(2L).vorName("Mathew").nachName("klaus").build());
      mockMvc = MockMvcBuilders.standaloneSetup(besitzerController).build();
    }

    @Test
    void listBesitzer() throws Exception {
        verifyNoInteractions(besitzerService);
        when(besitzerService.findAll()).thenReturn(besitzerSet);
        mockMvc.perform(MockMvcRequestBuilders.get("/besitzer/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("besitzer/index"))
                .andExpect(model().attribute("besitzer",hasSize(2)));
    }
}