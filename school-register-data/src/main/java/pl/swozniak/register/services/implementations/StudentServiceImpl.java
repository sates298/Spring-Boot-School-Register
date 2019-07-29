package pl.swozniak.register.services.implementations;

import org.springframework.stereotype.Service;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.repositories.StudentRepository;
import pl.swozniak.register.services.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student save(Student object) {
        return studentRepository.save(object);
    }

    @Override
    public void delete(Student object) {
        studentRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
