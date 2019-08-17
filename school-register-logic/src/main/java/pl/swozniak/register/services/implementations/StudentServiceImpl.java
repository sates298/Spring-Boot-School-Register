package pl.swozniak.register.services.implementations;

import org.springframework.stereotype.Service;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.mappers.StudentMapper;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.repositories.StudentRepository;
import pl.swozniak.register.services.interfaces.StudentService;
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
    public StudentDTO saveDTO(StudentDTO studentDTO){
        return save(studentMapper.studentDTOToStudent(studentDTO));
    }

    @Override
    public List<StudentDTO> findAllByClassId(Long classId) {
        return studentRepository
                .findAllBySchoolClassId(classId)
                .stream()
                .map(studentMapper::studentToStudentDTO)
                .collect(Collectors.toList());
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
    public List<StudentDTO> findStudentsByParentId(Long parentId) {
        return studentRepository
                .findAllByParentId(parentId)
                .stream()
                .map(studentMapper::studentToStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO patch(Long id, StudentDTO student) throws ResourceNotFoundException{

        return studentMapper.studentToStudentDTO(studentRepository.findById(id).map(found -> {

            if(!found.getSchoolClass().getId()
                    .equals(student.getSchoolClass().getId())){

                found.setSchoolClass(
                        studentMapper
                        .studentDTOToStudent(student)
                        .getSchoolClass()
                );

                return studentRepository.save(found);
            }

            return found;
        }).orElseThrow(ResourceNotFoundException::new));
    }

}
