package pl.swozniak.register.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.dtos.TeacherDTO;
import pl.swozniak.register.mapper.TeacherMapper;
import pl.swozniak.register.model.Teacher;
import pl.swozniak.register.repositories.TeacherRepository;

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
class TeacherServiceImplTest {

    public static final long ID = 1L;

    @Mock
    TeacherRepository teacherRepository;

    @Mock
    TeacherMapper mapper;

    @InjectMocks
    TeacherServiceImpl service;

    private Teacher returnedTeacher;
    private TeacherDTO returnedDTO;

    @BeforeEach
    void setUp() {
        returnedTeacher = Teacher.builder().id(ID).build();
        returnedDTO = new TeacherDTO();
        returnedDTO.setId(ID);
    }

    @Test
    void findAll(){
        List<Teacher> returnedTeachers = new ArrayList<>();
        returnedTeachers.add(returnedTeacher);
        returnedTeachers.add(Teacher.builder().id(ID +1).build());

        when(teacherRepository.findAll()).thenReturn(returnedTeachers);
        when(mapper.teacherToTeacherDTO(any())).thenReturn(returnedDTO);

        List<TeacherDTO> teachers = service.findAll();

        assertNotNull(teachers);
        assertEquals(2, teachers.size());
    }

    @Test
    void findById() {
        when(teacherRepository.findById(anyLong())).thenReturn(Optional.of(returnedTeacher));
        when(mapper.teacherToTeacherDTO(any())).thenReturn(returnedDTO);

        TeacherDTO teacher = service.findById(ID);
        assertNotNull(teacher);
    }

    @Test
    void findByIdNotFound(){
        when(teacherRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(mapper.teacherToTeacherDTO(any())).thenReturn(null);

        TeacherDTO teacher = service.findById(ID);
        assertNull(teacher);
    }

    @Test
    void save() {
        Teacher teacherToSave = Teacher.builder().id(ID).build();

        when(teacherRepository.save(any())).thenReturn(returnedTeacher);
        when(mapper.teacherToTeacherDTO(any())).thenReturn(returnedDTO);

        TeacherDTO saved = service.save(teacherToSave);

        assertNotNull(saved);
        verify(teacherRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnedTeacher);

        verify(teacherRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);

        verify(teacherRepository, times(1)).deleteById(anyLong());
    }
}