package pl.swozniak.register.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.dtos.SchoolClassDTO;
import pl.swozniak.register.mappers.SchoolClassMapper;
import pl.swozniak.register.model.SchoolClass;
import pl.swozniak.register.repositories.SchoolClassRepository;
import pl.swozniak.register.services.exceptions.ResourceNotFoundException;

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
class SchoolClassServiceImplTest {

    public static final long ID = 1L;

    @Mock
    SchoolClassRepository schoolClassRepository;

    @Mock
    SchoolClassMapper mapper;

    @InjectMocks
    SchoolClassServiceImpl service;

    private SchoolClass returnedSchoolClass;
    private SchoolClassDTO returnedDTO;

    @BeforeEach
    void setUp() {
        returnedSchoolClass = SchoolClass.builder().id(ID).build();
        returnedDTO = new SchoolClassDTO();
        returnedDTO.setId(ID);
    }

    @Test
    void findAll() {
        List<SchoolClass> schoolClassList = new ArrayList<>();
        schoolClassList.add(returnedSchoolClass);
        schoolClassList.add(SchoolClass.builder().id(ID + 1).build());
        schoolClassList.add(SchoolClass.builder().id(ID + 2).build());


        when(schoolClassRepository.findAll()).thenReturn(schoolClassList);
        when(mapper.schoolClassToSchoolClassDTO(any())).thenReturn(returnedDTO);

        List<SchoolClassDTO> schoolClasses = service.findAll();

        assertNotNull(schoolClasses);
        assertEquals(3, schoolClasses.size());
    }

    @Test
    void findById() {
        when(schoolClassRepository.findById(anyLong())).thenReturn(Optional.of(returnedSchoolClass));
        when(mapper.schoolClassToSchoolClassDTO(any())).thenReturn(returnedDTO);

        SchoolClassDTO lesson = service.findById(ID);
        assertNotNull(lesson);
    }

    @Test
    void findByIdNotFound(){
        when(schoolClassRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                service.findById(ID));
    }



    @Test
    void save() {
        SchoolClass schoolClassToSave = SchoolClass.builder().id(ID).build();

        when(schoolClassRepository.save(any())).thenReturn(returnedSchoolClass);
        when(mapper.schoolClassToSchoolClassDTO(any())).thenReturn(returnedDTO);

        SchoolClassDTO saved = service.save(schoolClassToSave);

        assertNotNull(saved);
        verify(schoolClassRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnedSchoolClass);

        verify(schoolClassRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);

        verify(schoolClassRepository, times(1)).deleteById(anyLong());
    }

}