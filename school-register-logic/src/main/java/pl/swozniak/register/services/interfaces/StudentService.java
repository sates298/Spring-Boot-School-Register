package pl.swozniak.register.services.interfaces;

import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.Student;

import java.util.List;

public interface StudentService extends CrudService<Student, StudentDTO, Long>{
    StudentDTO saveDTO(StudentDTO studentDTO);
    List<StudentDTO> findAllByClassId(Long classId);
    List<StudentDTO> findStudentsByParentId(Long parentId);
    StudentDTO patch(Long id, StudentDTO student);
}
