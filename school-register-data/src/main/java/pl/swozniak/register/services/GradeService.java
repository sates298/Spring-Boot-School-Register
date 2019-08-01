package pl.swozniak.register.services;


import pl.swozniak.register.model.Grade;
import pl.swozniak.register.model.Subject;

public interface GradeService {
    Grade findById(Long id);
    Grade save(Grade grade);
    void delete(Grade grade);
    void deleteById(Long id);
}
