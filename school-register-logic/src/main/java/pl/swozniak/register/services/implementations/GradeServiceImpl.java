package pl.swozniak.register.services.implementations;

import org.springframework.stereotype.Service;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.mappers.GradeMapper;
import pl.swozniak.register.model.Grade;
import pl.swozniak.register.model.enums.GradeValue;
import pl.swozniak.register.model.enums.SubjectName;
import pl.swozniak.register.repositories.GradeRepository;
import pl.swozniak.register.services.GradeService;
import pl.swozniak.register.services.exceptions.ResourceNotFoundException;
import pl.swozniak.register.services.gradestrategy.ProcessNewGradeBadBehavior;
import pl.swozniak.register.services.gradestrategy.NewGradeProcessor;
import pl.swozniak.register.services.gradestrategy.ProcessNewPositiveGrade;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private final NewGradeProcessor newGradeProcessor;

    public GradeServiceImpl(GradeRepository gradeRepository, GradeMapper gradeMapper, NewGradeProcessor newGradeProcessor) {
        this.gradeRepository = gradeRepository;
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

}
