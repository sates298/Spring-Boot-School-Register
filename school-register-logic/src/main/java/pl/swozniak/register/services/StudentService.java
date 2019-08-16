package pl.swozniak.register.services;

import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.Student;

import java.util.List;

public interface StudentService extends CrudService<Student, StudentDTO, Long>{
    List<StudentDTO> findStudentsByParentId(Long parentId);
    StudentDTO patch(Long id, Student student);
}
