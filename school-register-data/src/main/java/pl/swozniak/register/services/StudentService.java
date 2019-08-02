package pl.swozniak.register.services;

import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.Student;

public interface StudentService{
    StudentDTO findById(Long id);
    StudentDTO save(Student student);
}
