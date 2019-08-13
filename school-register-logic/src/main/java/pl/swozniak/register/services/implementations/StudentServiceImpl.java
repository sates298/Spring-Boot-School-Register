package pl.swozniak.register.services.implementations;

import org.springframework.stereotype.Service;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.mappers.StudentMapper;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.repositories.StudentRepository;
import pl.swozniak.register.services.StudentService;
import pl.swozniak.register.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository
                .findAll()
                .stream()
                .map(studentMapper::studentToStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO findById(Long id) {
        Student found = studentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return studentMapper.studentToStudentDTO(found);
    }

    @Override
    public StudentDTO save(Student object) {
        Student saved = studentRepository.save(object);
        return studentMapper.studentToStudentDTO(saved);
    }

    @Override
    public void delete(Student object) {
        studentRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO patch(Long id, Student student) throws ResourceNotFoundException{
        Student found = studentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        if(!found.getSchoolClass().getId()
                .equals(student.getSchoolClass().getId())){

            found.setSchoolClass(student.getSchoolClass());
            Student saved = studentRepository.save(found);

            return studentMapper.studentToStudentDTO(saved);
        }

        return studentMapper.studentToStudentDTO(found);
    }

}
