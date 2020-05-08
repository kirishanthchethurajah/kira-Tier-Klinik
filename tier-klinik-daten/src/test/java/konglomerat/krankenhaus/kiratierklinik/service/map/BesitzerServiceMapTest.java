package konglomerat.krankenhaus.kiratierklinik.service.map;

import konglomerat.krankenhaus.kiratierklinik.model.Besitzer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BesitzerServiceMapTest {

    BesitzerServiceMap besitzerServiceMap;

    @BeforeEach
    void setUp() {
        besitzerServiceMap = new BesitzerServiceMap(new TierServiceMap(), new TierTypServiceMap() );
        besitzerServiceMap.save(Besitzer.builder().id(1L).vorName("Richard").adresse("nirgendwo").nachName("Bauer").build());
    }

    @Test
    void findAll() {
        assertEquals(1,besitzerServiceMap.findAll().size());

    }

    @Test
    void findById() {
        assertEquals(1L, besitzerServiceMap.findById(1L).getId());
    }

    @Test
    void deleteById() {
        besitzerServiceMap.deleteById(1L);
        assertNull(besitzerServiceMap.findById(1L));
    }

    @Test
    void delete() {
        if(besitzerServiceMap.findById(1L).getId()!=null){
            besitzerServiceMap.delete(besitzerServiceMap.findById(1L));

        }
        assertNull(besitzerServiceMap.findById(1L));
    }

    @Test
    void save() {

        assertNotNull(besitzerServiceMap.save(Besitzer.builder().id(2L).vorName("Rachael").adresse("jetzt hier").build()));


    }

    @Test
    void findByNachName() {
        Besitzer besitzer= besitzerServiceMap.findByNachName("Bauer");
        assertNotNull(besitzer);
        assertEquals("Bauer",besitzer.getNachName());

    }

    @Test
    void findByNachNameNotFound() {
        assertNull(besitzerServiceMap.findByNachName("Tim"));

    }
}