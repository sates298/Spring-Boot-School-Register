package pl.swozniak.register.services;


import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.model.Grade;

public interface GradeService {
    GradeDTO findById(Long id);
    GradeDTO save(Grade grade);
    void delete(Grade grade);
    void deleteById(Long id);
}
