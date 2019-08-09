package pl.swozniak.register.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.mappers.StudentMapper;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.repositories.StudentRepository;
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
class StudentServiceImplTest {

    public static final long ID = 1L;

    @Mock
    StudentRepository studentRepository;

    @Mock
    StudentMapper mapper;

    @InjectMocks
    StudentServiceImpl service;

    private Student returnedStudent;
    private StudentDTO returnedDTO;

    @BeforeEach
    void setUp() {
        returnedStudent = Student.builder().id(ID).build();
        returnedDTO = new StudentDTO();
        returnedDTO.setId(ID);
    }

    @Test
    void findAll(){
        List<Student> returnedStudents = new ArrayList<>();
        returnedStudents.add(returnedStudent);
        returnedStudents.add(Student.builder().id(ID +1).build());

        when(studentRepository.findAll()).thenReturn(returnedStudents);
        when(mapper.studentToStudentDTO(any())).thenReturn(returnedDTO);

        List<StudentDTO> students = service.findAll();

        assertNotNull(students);
        assertEquals(2, students.size());
    }

    @Test
    void findById() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(returnedStudent));
        when(mapper.studentToStudentDTO(any())).thenReturn(returnedDTO);

        StudentDTO student = service.findById(ID);
        assertNotNull(student);
    }

    @Test
    void findByIdNotFound(){
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                service.findById(ID));
    }

    @Test
    void save() {
        Student studentToSave = Student.builder().id(ID).build();

        when(studentRepository.save(any())).thenReturn(returnedStudent);
        when(mapper.studentToStudentDTO(any())).thenReturn(returnedDTO);

        StudentDTO saved = service.save(studentToSave);

        assertNotNull(saved);
        verify(studentRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnedStudent);

        verify(studentRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);

        verify(studentRepository, times(1)).deleteById(anyLong());
    }
}