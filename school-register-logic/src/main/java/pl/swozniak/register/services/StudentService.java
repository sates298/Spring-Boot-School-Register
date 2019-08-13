package pl.swozniak.register.services;

import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.Student;

public interface StudentService extends CrudService<Student, StudentDTO, Long>{
    StudentDTO patch(Long id, Student student);
}
