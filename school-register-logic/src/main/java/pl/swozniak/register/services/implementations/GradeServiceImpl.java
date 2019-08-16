package pl.swozniak.register.services.implementations;

import org.springframework.stereotype.Service;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.mappers.GradeMapper;
import pl.swozniak.register.model.Grade;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.repositories.GradeRepository;
import pl.swozniak.register.repositories.StudentRepository;
import pl.swozniak.register.services.GradeService;
import pl.swozniak.register.services.exceptions.ResourceNotFoundException;
import pl.swozniak.register.gradeprocessing.NewGradeProcessor;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final GradeMapper gradeMapper;
    private final NewGradeProcessor newGradeProcessor;

    public GradeServiceImpl(GradeRepository gradeRepository, StudentRepository studentRepository,
                            GradeMapper gradeMapper, NewGradeProcessor newGradeProcessor) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.gradeMapper = gradeMapper;
        this.newGradeProcessor = newGradeProcessor;
    }

    @Override
    public List<GradeDTO> findAll() {
        return gradeRepository
                .findAll()
                .stream()
                .map(gradeMapper::gradeToGradeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GradeDTO findById(Long id) {
        Grade found = gradeRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return gradeMapper.gradeToGradeDTO(found);
    }

    @Override
    public GradeDTO saveGrade(Grade grade, Long studentId) {
        Student owner = studentRepository.findById(studentId).orElseThrow(ResourceNotFoundException::new);
        if(grade.getStudent() == null){
            grade.setStudent(owner);
        }

        return save(grade);
    }

    @Override
    public GradeDTO save(Grade object) {
        Grade saved = gradeRepository.save(object);
        GradeDTO mapped = gradeMapper.gradeToGradeDTO(saved);

        return newGradeProcessor.processNewGrade(mapped);
    }

    @Override
    public void delete(Grade object) {
        gradeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        gradeRepository.deleteById(id);
    }

    @Override
    public GradeDTO put(Long id, Grade grade) {
        GradeDTO dto = gradeMapper.gradeToGradeDTO(grade);
        dto.setId(id);
        Grade transfer = gradeMapper.gradeDTOToGrade(dto);

        return save(transfer);
    }

    @Override
    public List<GradeDTO> findAllByStudentId(Long studentId) {
        return gradeRepository
                .findAllByStudentId(studentId)
                .stream()
                .map(gradeMapper::gradeToGradeDTO)
                .collect(Collectors.toList());
    }
}
