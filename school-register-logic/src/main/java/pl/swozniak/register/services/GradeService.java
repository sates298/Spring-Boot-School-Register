package pl.swozniak.register.services;


import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.model.Grade;

public interface GradeService extends CrudService<Grade,GradeDTO, Long> {
    GradeDTO saveGrade(Grade grade, Long studentId);
    GradeDTO put(Long id, Grade grade);
}
