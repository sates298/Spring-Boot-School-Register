package pl.swozniak.register.services.implementations;

import org.springframework.stereotype.Service;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.mapper.StudentMapper;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.repositories.StudentRepository;
import pl.swozniak.register.services.StudentService;


@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentDTO findById(Long id) {
        Student found = studentRepository.findById(id).orElse(null);
        return studentMapper.studentToStudentDTO(found);
    }

    @Override
    public StudentDTO save(Student object) {
        Student saved = studentRepository.save(object);
        return studentMapper.studentToStudentDTO(saved);
    }

}
