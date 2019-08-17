package pl.swozniak.register.services.implementations;

import org.springframework.stereotype.Service;
import pl.swozniak.register.dtos.SubjectDTO;
import pl.swozniak.register.mappers.SubjectMapper;
import pl.swozniak.register.model.Subject;
import pl.swozniak.register.repositories.SubjectRepository;
import pl.swozniak.register.services.interfaces.SubjectService;
import pl.swozniak.register.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public List<SubjectDTO> findAll() {
        return subjectRepository
                .findAll()
                .stream()
                .map(subjectMapper::subjectToSubjectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDTO findById(Long id) {
        Subject found = subjectRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return subjectMapper.subjectToSubjectDTO(found);
    }

    @Override
    public SubjectDTO save(Subject object) {
        Subject saved = subjectRepository.save(object);
        return subjectMapper.subjectToSubjectDTO(saved);
    }

    @Override
    public void delete(Subject object) {
        subjectRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        subjectRepository.deleteById(id);
    }
}
