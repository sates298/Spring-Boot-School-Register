package pl.swozniak.register.services.implementations;

import org.springframework.stereotype.Service;
import pl.swozniak.register.dtos.SchoolClassDTO;
import pl.swozniak.register.mapper.SchoolClassMapper;
import pl.swozniak.register.model.SchoolClass;
import pl.swozniak.register.repositories.SchoolClassRepository;
import pl.swozniak.register.services.SchoolClassService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolClassServiceImpl implements SchoolClassService {

    private final SchoolClassRepository schoolClassRepository;
    private final SchoolClassMapper schoolClassMapper;

    public SchoolClassServiceImpl(SchoolClassRepository schoolClassRepository, SchoolClassMapper schoolClassMapper) {
        this.schoolClassRepository = schoolClassRepository;
        this.schoolClassMapper = schoolClassMapper;
    }

    @Override
    public List<SchoolClassDTO> findAll() {
        return schoolClassRepository.findAll()
                .stream()
                .map(schoolClassMapper::schoolClassToSchoolClassDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SchoolClassDTO findById(Long id) {
        SchoolClass found = schoolClassRepository.findById(id).orElse(null);
        return schoolClassMapper.schoolClassToSchoolClassDTO(found);
    }

    @Override
    public SchoolClassDTO save(SchoolClass object) {
        SchoolClass saved = schoolClassRepository.save(object);
        return schoolClassMapper.schoolClassToSchoolClassDTO(saved);
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
