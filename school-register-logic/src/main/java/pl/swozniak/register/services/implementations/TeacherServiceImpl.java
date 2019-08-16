package pl.swozniak.register.services.implementations;

import org.springframework.stereotype.Service;
import pl.swozniak.register.dtos.TeacherDTO;
import pl.swozniak.register.mappers.TeacherMapper;
import pl.swozniak.register.model.Teacher;
import pl.swozniak.register.repositories.TeacherRepository;
import pl.swozniak.register.services.TeacherService;
import pl.swozniak.register.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }


    @Override
    public List<TeacherDTO> findAll() {
        return teacherRepository
                .findAll()
                .stream()
                .map(teacherMapper::teacherToTeacherDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO findById(Long id) {
        Teacher found = teacherRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return teacherMapper.teacherToTeacherDTO(found);
    }

    @Override
    public TeacherDTO save(Teacher object) {
        Teacher saved = teacherRepository.save(object);
        return teacherMapper.teacherToTeacherDTO(saved);
    }

    @Override
    public void delete(Teacher object) {
        teacherRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherDTO> findBySubjectId(Long subjectId) {
        return teacherRepository
                .findAllBySubjectId(subjectId)
                .stream()
                .map(teacherMapper::teacherToTeacherDTO)
                .collect(Collectors.toList());
    }
}
