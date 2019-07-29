package pl.swozniak.register.services.implementations;

import org.springframework.stereotype.Service;
import pl.swozniak.register.model.SchoolClass;
import pl.swozniak.register.repositories.SchoolClassRepository;
import pl.swozniak.register.services.SchoolClassService;

import java.util.List;

@Service
public class SchoolClassServiceImpl implements SchoolClassService {

    private final SchoolClassRepository schoolClassRepository;

    public SchoolClassServiceImpl(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    @Override
    public List<SchoolClass> findAll() {
        return schoolClassRepository.findAll();
    }

    @Override
    public SchoolClass findById(Long id) {
        return schoolClassRepository.findById(id).orElse(null);
    }

    @Override
    public SchoolClass save(SchoolClass object) {
        return schoolClassRepository.save(object);
    }

    @Override
    public void delete(SchoolClass object) {
        schoolClassRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        schoolClassRepository.deleteById(id);
    }
}
