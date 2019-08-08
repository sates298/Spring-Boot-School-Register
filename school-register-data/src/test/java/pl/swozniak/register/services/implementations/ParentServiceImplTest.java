package pl.swozniak.register.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.mapper.ParentMapper;
import pl.swozniak.register.model.Parent;
import pl.swozniak.register.repositories.ParentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParentServiceImplTest {

    public static final long ID = 1L;

    @Mock
    ParentRepository parentRepository;

    @Mock
    ParentMapper mapper;

    @InjectMocks
    ParentServiceImpl service;

    private Parent returnedParent;
    private ParentDTO returnedDTO;

    @BeforeEach
    void setUp() {
        returnedParent = Parent.builder().id(ID).build();
        returnedDTO = new ParentDTO();
        returnedDTO.setId(ID);

    }

    @Test
    void findAll(){
        List<Parent> returnedParents = new ArrayList<>();
        returnedParents.add(returnedParent);
        returnedParents.add(Parent.builder().id(ID +1).build());

        when(parentRepository.findAll()).thenReturn(returnedParents);
        when(mapper.parentToParentDTO(any())).thenReturn(returnedDTO);

        List<ParentDTO> parents = service.findAll();

        assertNotNull(parents);
        assertEquals(2, parents.size());
    }

    @Test
    void findById() {
        when(parentRepository.findById(anyLong())).thenReturn(Optional.of(returnedParent));
        when(mapper.parentToParentDTO(any())).thenReturn(returnedDTO);

        ParentDTO parent = service.findById(ID);
        assertNotNull(parent);
    }

    @Test
    void findByIdNotFound(){
        when(parentRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(mapper.parentToParentDTO(any())).thenReturn(null);

        ParentDTO parent = service.findById(ID);
        assertNull(parent);
    }

    @Test
    void save() {
        Parent parentToSave = Parent.builder().id(ID).build();

        when(parentRepository.save(any())).thenReturn(returnedParent);
        when(mapper.parentToParentDTO(any())).thenReturn(returnedDTO);

        ParentDTO saved = service.save(parentToSave);

        assertNotNull(saved);
        verify(parentRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnedParent);

        verify(parentRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);

        verify(parentRepository, times(1)).deleteById(anyLong());
    }
}