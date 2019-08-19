package pl.swozniak.register.services.implementations;

import org.springframework.stereotype.Service;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.gradeprocessing.updater.GradeUpdater;
import pl.swozniak.register.mappers.GradeMapper;
import pl.swozniak.register.model.Grade;
import pl.swozniak.register.repositories.GradeRepository;
import pl.swozniak.register.services.ServiceManager;
import pl.swozniak.register.services.interfaces.GradeService;
import pl.swozniak.register.exceptions.ResourceNotFoundException;
import pl.swozniak.register.gradeprocessing.aftersaveprocessing.NewGradeProcessor;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;

    private final NewGradeProcessor newGradeProcessor;
    private final GradeUpdater gradeUpdater;

    private final ServiceManager manager;

    public GradeServiceImpl(GradeRepository gradeRepository, GradeMapper gradeMapper,
                            ServiceManager manager, NewGradeProcessor newGradeProcessor, GradeUpdater gradeUpdater) {
        this.gradeRepository = gradeRepository;
        this.gradeMapper = gradeMapper;
        this.manager = manager;
        this.newGradeProcessor = newGradeProcessor;
        this.gradeUpdater = gradeUpdater;
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
    public GradeDTO saveDTO(GradeDTO gradeDTO, Long studentId) {
        StudentDTO owner = manager.findStudentById(studentId);
        if (gradeDTO.getStudent() == null) {
            gradeDTO.setStudent(owner);
        }

        return save(gradeMapper.gradeDTOToGrade(gradeDTO));
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
    public GradeDTO patch(Long id, GradeDTO grade) {
        return gradeRepository.findById(id).map(found -> {
            GradeDTO updated =
                    gradeUpdater.updateGrade(gradeMapper.gradeToGradeDTO(found),grade);
            return saveDTO(updated, updated.getOwnerId());
        }).orElseThrow(ResourceNotFoundException::new);
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
