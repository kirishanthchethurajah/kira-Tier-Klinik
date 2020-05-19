package konglomerat.krankenhaus.kiratierklinik.springDataJpa;

import konglomerat.krankenhaus.kiratierklinik.model.Besitzer;
import konglomerat.krankenhaus.kiratierklinik.repositories.BesitzerRepository;
import konglomerat.krankenhaus.kiratierklinik.repositories.TierRepository;
import konglomerat.krankenhaus.kiratierklinik.repositories.TierTypRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BesitzerSDJpaServiceTest {
    @Mock
    BesitzerRepository besitzerRepository;
    @Mock
    TierRepository tierRepository;
    @Mock
    TierTypRepository tierTypRepository;

    @InjectMocks
    BesitzerSDJpaService besitzerSDJpaService;

    Besitzer besitzer;

    @BeforeEach
    void setUp(){
        besitzer = Besitzer.builder().kId(4L).vorName("Dom").nachName("Terrence").build();

    }

    @Test
    void findByNachName() {
        assertNull(besitzerSDJpaService.findByNachName("Musk"));
        when(besitzerRepository.findByNachName(any())).thenReturn(besitzer);
        assertEquals("Terrence", besitzerSDJpaService.findByNachName("Terrence").getNachName());






    }

    @Test
    void findById() {
        when(besitzerRepository.findById(any())).thenReturn(Optional.ofNullable(besitzer));

        assertEquals(4L,besitzerSDJpaService.findById(4L).getKId());

    }

    @Test
    void findAll() {
        Set<Besitzer> besitzers1 = new HashSet<>();
        besitzers1.add(Besitzer.builder().kId(10L).vorName("Mathew").nachName("klaus").build());
        besitzers1.add(Besitzer.builder().kId(5L).vorName("Christopher").nachName("Nolan").build());
        when(besitzerRepository.findAll()).thenReturn(besitzers1);
        assertEquals(2,besitzerSDJpaService.findAll().size());
    }

    @Test
    void save() {
        Besitzer check= Besitzer.builder().kId(10L).vorName("Christopher").nachName("Nolan").build();
        when(besitzerRepository.save(any())).thenReturn(check);
        Besitzer tmp = besitzerSDJpaService.save(check);
        assertNotNull(tmp);
        verify(besitzerRepository).save(any());

    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}