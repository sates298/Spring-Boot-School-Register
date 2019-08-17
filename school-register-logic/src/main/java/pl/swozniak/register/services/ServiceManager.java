package pl.swozniak.register.services;

import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.services.interfaces.StudentService;

@Component
public class ServiceManager {

    private final StudentService studentService;

    public ServiceManager(StudentService studentService) {
        this.studentService = studentService;
    }

    public StudentDTO findStudentById(Long studentId){
        return studentService.findById(studentId);
    }
}
