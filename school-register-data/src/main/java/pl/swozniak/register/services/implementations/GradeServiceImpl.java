package pl.swozniak.register.services.implementations;

import org.springframework.stereotype.Service;
import pl.swozniak.register.model.Grade;
import pl.swozniak.register.repositories.GradeRepository;
import pl.swozniak.register.services.GradeService;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Grade findById(Long id) {
        return gradeRepository.findById(id).orElse(null);
    }

    @Override
    public Grade save(Grade object) {
        return gradeRepository.save(object);
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
    public List<Grade> findAllByStudentId(Long studentId) {
        return gradeRepository.findAllByStudent_Id(studentId);
    }
}
