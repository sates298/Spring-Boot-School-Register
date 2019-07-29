package pl.swozniak.register.services;

import pl.swozniak.register.model.Grade;

import java.util.List;

public interface GradeService extends CrudService<Grade, Long> {

    List<Grade> findAllByStudentId(Long studentId);
}
