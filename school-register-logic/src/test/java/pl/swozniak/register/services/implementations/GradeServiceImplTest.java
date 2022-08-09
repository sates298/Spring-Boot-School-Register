package pl.swozniak.register.services.implementations;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.dtos.GradeDTO;

import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.gradeprocessing.updater.GradeUpdater;
import pl.swozniak.register.mappers.GradeMapper;
import pl.swozniak.register.model.Grade;
import pl.swozniak.register.repositories.GradeRepository;
import pl.swozniak.register.services.ServiceManager;
import pl.swozniak.register.exceptions.ResourceNotFoundException;
import pl.swozniak.register.gradeprocessing.aftersaveprocessing.NewGradeProcessor;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GradeServiceImplTest {

    public static final long ID = 1L;
    public static final String NOTES = "notes";

    @Mock
    GradeRepository gradeRepository;

    @Mock
    ServiceManager manager;

    @Mock
    GradeMapper mapper;

    @Mock
    NewGradeProcessor newGradeProcessor;

    @Mock
    GradeUpdater updater;

    @InjectMocks
    GradeServiceImpl service;

    private Grade returnedGrade;
    private GradeDTO returnedDTO;

    @BeforeEach
    void setUp() {
        returnedGrade = Grade.builder().id(ID).build();
        returnedDTO = new GradeDTO();
        returnedDTO.setId(ID);
    }


    @Test
    void findAll(){
        List<Grade> returnedGrades = new ArrayList<>();
        returnedGrades.add(returnedGrade);
        returnedGrades.add(Grade.builder().id(ID +1).build());

        when(gradeRepository.findAll()).thenReturn(returnedGrades);
        when(mapper.gradeToGradeDTO(any())).thenReturn(returnedDTO);


        List<GradeDTO> grades = service.findAll();

        assertNotNull(grades);
        assertEquals(2, grades.size());
    }

    @Test
    void findById() {
        when(gradeRepository.findById(anyLong())).thenReturn(Optional.of(returnedGrade));
        when(mapper.gradeToGradeDTO(any())).thenReturn(returnedDTO);

        GradeDTO grade = service.findById(ID);
        assertNotNull(grade);
    }

    @Test
    void findByIdNotFound(){
        when(gradeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                service.findById(ID));
    }


    @Test
    void saveDTOFoundOwner(){
        StudentDTO owner = new StudentDTO();
        owner.setId(ID);

        when(gradeRepository.save(any())).thenReturn(returnedGrade);
        when(manager.findStudentById(anyLong())).thenReturn(owner);
        when(mapper.gradeToGradeDTO(any())).thenReturn(returnedDTO);
        when(mapper.gradeDTOToGrade(any())).thenReturn(returnedGrade);
        when(newGradeProcessor.processNewGrade(any())).thenReturn(returnedDTO);

        GradeDTO saved = service.saveDTO(returnedDTO, ID);

        assertNotNull(saved);
        assertNotNull(returnedDTO.getStudent());
        assertEquals(Long.valueOf(ID), returnedDTO.getStudent().getId());

        verify(gradeRepository).save(any());
    }

    @Test
    void savedDTONotFoundOwner(){
        when(manager.findStudentById(anyLong())).thenThrow(new ResourceNotFoundException());

        assertThrows(ResourceNotFoundException.class, () ->
                service.saveDTO(returnedDTO, ID));
    }

    @Test
    void save() {
        Grade gradeToSave = Grade.builder().id(ID).build();

        when(gradeRepository.save(any())).thenReturn(returnedGrade);
        when(mapper.gradeToGradeDTO(any())).thenReturn(returnedDTO);
        when(newGradeProcessor.processNewGrade(any())).thenReturn(returnedDTO);

        GradeDTO saved = service.save(gradeToSave);

        assertNotNull(saved);
        verify(gradeRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnedGrade);

        verify(gradeRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);

        verify(gradeRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void patchNotFound(){
        when(gradeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                service.patch(ID,returnedDTO));
    }


    @Test
    void patch() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(ID);
        returnedDTO.setStudent(studentDTO);

        when(gradeRepository.findById(anyLong())).thenReturn(Optional.of(returnedGrade));
        when(gradeRepository.save(any())).thenReturn(returnedGrade);

        when(updater.updateGrade(any(), any())).thenReturn(returnedDTO);

        when(manager.findStudentById(anyLong())).thenReturn(new StudentDTO());

        when(mapper.gradeDTOToGrade(any())).thenReturn(returnedGrade);
        when(mapper.gradeToGradeDTO(any())).thenReturn(returnedDTO);

        when(newGradeProcessor.processNewGrade(any())).thenReturn(returnedDTO);

        GradeDTO returned = service.patch(ID, returnedDTO);

        assertNotNull(returned);

        verify(updater).updateGrade(any(), any());
        verify(gradeRepository).save(any());

    }

    @Test
    void findAllByStudentId(){
        Grade grade = Grade.builder().id(ID + 1).build();

        List<Grade> grades = Arrays.asList(grade, returnedGrade);

        when(gradeRepository.findAllByStudentId(anyLong())).thenReturn(grades);
        when(mapper.gradeToGradeDTO(any())).thenReturn(returnedDTO);

        List<GradeDTO> returned = service.findAllByStudentId(ID);

        assertNotNull(returned);
        assertEquals(2, returned.size());
    }


}