package pl.swozniak.register.services.implementations;

import org.springframework.stereotype.Service;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.mapper.GradeMapper;
import pl.swozniak.register.model.Grade;
import pl.swozniak.register.repositories.GradeRepository;
import pl.swozniak.register.services.GradeService;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;

    public GradeServiceImpl(GradeRepository gradeRepository, GradeMapper gradeMapper) {
        this.gradeRepository = gradeRepository;
        this.gradeMapper = gradeMapper;
    }

    @Override
    public GradeDTO findById(Long id) {
        Grade found = gradeRepository.findById(id).orElse(null);
        return gradeMapper.gradeToGradeDTO(found);
    }

    @Override
    public GradeDTO save(Grade object) {
        Grade saved =  gradeRepository.save(object);
        return gradeMapper.gradeToGradeDTO(saved);
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
