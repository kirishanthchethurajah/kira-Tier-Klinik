package konglomerat.krankenhaus.kiratierklinik.service;

import java.util.Set;

public interface BaseCrudService<T,ID> {
    T findById(ID id);
    Set<T> findAll();
    T save(T object);
    void delete(T object);
    void deleteById(ID id);


}
