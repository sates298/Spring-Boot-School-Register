package pl.swozniak.register.services.implementations;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.dtos.SubjectDTO;
import pl.swozniak.register.mapper.SubjectMapper;
import pl.swozniak.register.model.Subject;
import pl.swozniak.register.repositories.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubjectServiceImplTest {
    public static final long ID = 1L;

    @Mock
    SubjectRepository subjectRepository;

    @Mock
    SubjectMapper mapper;

    @InjectMocks
    SubjectServiceImpl service;

    private Subject returnedSubject;
    private SubjectDTO returnedDTO;

    @BeforeEach
    void setUp() {
        returnedSubject = Subject.builder().id(ID).build();
        returnedDTO = new SubjectDTO();
        returnedDTO.setId(ID);
    }

    @Test
    void findAll(){
        List<Subject> returnedSubjects = new ArrayList<>();
        returnedSubjects.add(returnedSubject);
        returnedSubjects.add(Subject.builder().id(ID +1).build());

        when(subjectRepository.findAll()).thenReturn(returnedSubjects);
        when(mapper.subjectToSubjectDTO(any())).thenReturn(returnedDTO);

        List<SubjectDTO> subjects = service.findAll();

        assertNotNull(subjects);
        assertEquals(2, subjects.size());
    }

    @Test
    void findById() {
        when(subjectRepository.findById(anyLong())).thenReturn(Optional.of(returnedSubject));
        when(mapper.subjectToSubjectDTO(any())).thenReturn(returnedDTO);

        SubjectDTO subject = service.findById(ID);
        assertNotNull(subject);
    }

    @Test
    void findByIdNotFound(){
        when(subjectRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(mapper.subjectToSubjectDTO(any())).thenReturn(null);

        SubjectDTO subject = service.findById(ID);
        assertNull(subject);
    }

    @Test
    void save() {
        Subject subjectToSave = Subject.builder().id(ID).build();

        when(subjectRepository.save(any())).thenReturn(returnedSubject);
        when(mapper.subjectToSubjectDTO(any())).thenReturn(returnedDTO);

        SubjectDTO saved = service.save(subjectToSave);

        assertNotNull(saved);
        verify(subjectRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnedSubject);

        verify(subjectRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);

        verify(subjectRepository, times(1)).deleteById(anyLong());
    }

}