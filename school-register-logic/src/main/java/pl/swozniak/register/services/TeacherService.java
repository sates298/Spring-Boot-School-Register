package pl.swozniak.register.services;

import pl.swozniak.register.dtos.TeacherDTO;
import pl.swozniak.register.model.Teacher;

import java.util.List;

public interface TeacherService extends CrudService<Teacher, TeacherDTO, Long> {
    List<TeacherDTO> findBySubjectId(Long subjectId);
}
