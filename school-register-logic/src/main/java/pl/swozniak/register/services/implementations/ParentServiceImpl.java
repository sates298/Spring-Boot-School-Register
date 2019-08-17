package pl.swozniak.register.services.implementations;

import org.springframework.stereotype.Service;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.mappers.ParentMapper;
import pl.swozniak.register.model.Parent;
import pl.swozniak.register.repositories.ParentRepository;
import pl.swozniak.register.services.ParentService;
import pl.swozniak.register.services.StudentService;
import pl.swozniak.register.services.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;
    private final ParentMapper parentMapper;

    public ParentServiceImpl(ParentRepository parentRepository, ParentMapper parentMapper) {
        this.parentRepository = parentRepository;
        this.parentMapper = parentMapper;
    }

    @Override
    public List<ParentDTO> findAll() {
        return parentRepository
                .findAll()
                .stream()
                .map(parentMapper::parentToParentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ParentDTO findById(Long id) {
        Parent found = parentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return parentMapper.parentToParentDTO(found);
    }

    @Override
    public ParentDTO save(Parent object) {
        Parent saved = parentRepository.save(object);
        return parentMapper.parentToParentDTO(saved);
    }

    @Override
    public void delete(Parent object) {
        parentRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        parentRepository.deleteById(id);
    }
}
