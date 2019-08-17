package pl.swozniak.register.services.interfaces;

import java.util.List;

public interface CrudService<T, DTO, ID> {
    List<DTO> findAll();
    DTO findById(ID id);
    DTO save(T object);
    void delete(T object);
    void deleteById(ID id);
}
