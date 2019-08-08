package pl.swozniak.register.services.implementations;

import org.springframework.stereotype.Service;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.mapper.GradeMapper;
import pl.swozniak.register.model.Grade;
import pl.swozniak.register.model.enums.GradeValue;
import pl.swozniak.register.model.enums.SubjectName;
import pl.swozniak.register.repositories.GradeRepository;
import pl.swozniak.register.services.GradeService;
import pl.swozniak.register.services.exceptions.ResourceNotFoundException;
import pl.swozniak.register.services.gradestrategy.NewGradeBadBehavior;
import pl.swozniak.register.services.gradestrategy.NewGradeRecorder;
import pl.swozniak.register.services.gradestrategy.NewGradeStrategy;
import pl.swozniak.register.services.gradestrategy.NewPositiveGrade;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private NewGradeRecorder newGradeRecorder;

    public GradeServiceImpl(GradeRepository gradeRepository, GradeMapper gradeMapper) {
        this.gradeRepository = gradeRepository;
        this.gradeMapper = gradeMapper;
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
    public GradeDTO save(Grade object) {
        Grade saved = gradeRepository.save(object);
        GradeDTO mapped = gradeMapper.gradeToGradeDTO(saved);

        newGradeRecorder = new NewGradeRecorder(
                mapped
                        .getSubject()
                        .getName()
                        .equals(SubjectName.BEHAVIOR.toString())
                        && mapped
                        .getGrade()
                        .equals(GradeValue.ONE.toString())
                        ? new NewGradeBadBehavior() : new NewPositiveGrade());

        return newGradeRecorder.checkNewGrade(mapped);
    }

    @Override
    public void delete(Grade object) {
        gradeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        gradeRepository.deleteById(id);
    }

}
