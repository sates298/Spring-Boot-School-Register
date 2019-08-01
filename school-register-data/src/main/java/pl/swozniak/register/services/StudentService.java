package pl.swozniak.register.services;

import pl.swozniak.register.model.Student;

public interface StudentService {
    Student findById(Long id);
    Student save(Student student);
}
