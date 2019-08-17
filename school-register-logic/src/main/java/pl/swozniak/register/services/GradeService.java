package pl.swozniak.register.services;


import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.model.Grade;

import java.util.List;

public interface GradeService extends CrudService<Grade,GradeDTO, Long> {
    GradeDTO saveDTO(GradeDTO grade, Long studentId);
    GradeDTO patch(Long id, GradeDTO grade);
    List<GradeDTO> findAllByStudentId(Long studentId);
}
