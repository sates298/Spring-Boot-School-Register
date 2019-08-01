package pl.swozniak.register.services;

import pl.swozniak.register.model.SchoolClass;

import java.util.List;

public interface SchoolClassService {
    List<SchoolClass> findAll();
    SchoolClass findById(Long id);
}
