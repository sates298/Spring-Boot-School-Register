package pl.swozniak.register.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.repositories.StudentRepository;

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

    @InjectMocks
    StudentServiceImpl service;

    private Student returnedStudent;

    @BeforeEach
    void setUp() {
        returnedStudent = Student.builder().id(ID).build();
    }


    @Test
    void findById() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(returnedStudent));

        Student student = service.findById(ID);
        assertNotNull(student);
    }

    @Test
    void findByIdNotFound(){
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        Student student = service.findById(ID);
        assertNull(student);
    }

    @Test
    void save() {
        Student studentToSave = Student.builder().id(ID).build();

        when(studentRepository.save(any())).thenReturn(returnedStudent);

        Student saved = service.save(studentToSave);

        assertNotNull(saved);
        verify(studentRepository).save(any());
    }
}