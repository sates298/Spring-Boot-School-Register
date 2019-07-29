package pl.swozniak.register.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.model.Grade;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.repositories.GradeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GradeServiceImplTest {

    public static final long ID = 1L;

    @Mock
    GradeRepository gradeRepository;

    @InjectMocks
    GradeServiceImpl service;

    private Grade returnGrade;

    @BeforeEach
    void setUp() {
        returnGrade = Grade.builder().id(ID).build();
    }

    @Test
    void findAll() {
        List<Grade> gradeList = new ArrayList<>();
        gradeList.add(returnGrade);
        gradeList.add(Grade.builder().id(ID + 1).build());
        gradeList.add(Grade.builder().id(ID + 2).build());

        when(gradeRepository.findAll()).thenReturn(gradeList);

        List<Grade> grades = service.findAll();

        assertNotNull(grades);
        assertEquals(3, grades.size());
    }

    @Test
    void findById() {
        when(gradeRepository.findById(anyLong())).thenReturn(Optional.of(returnGrade));

        Grade grade = service.findById(ID);
        assertNotNull(grade);
    }

    @Test
    void findByIdNotFound(){
        when(gradeRepository.findById(anyLong())).thenReturn(Optional.empty());

        Grade grade = service.findById(ID);
        assertNull(grade);
    }

    @Test
    void save() {
        Grade gradeToSave = Grade.builder().id(ID).build();

        when(gradeRepository.save(any())).thenReturn(returnGrade);

        Grade saved = service.save(gradeToSave);

        assertNotNull(saved);
        verify(gradeRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnGrade);

        verify(gradeRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);

        verify(gradeRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void findAllByStudentId(){
        Student student = Student.builder().id(ID).build();
        Student student1 = Student.builder().id(ID + 1).build();

        returnGrade.setStudent(student);
        Grade grade2 = Grade.builder().id(ID + 1).student(student).build();
        Grade grade3 = Grade.builder().id(ID + 2).student(student1).build();

        List<Grade> allGrades = List.of(returnGrade, grade2, grade3);

        when(gradeRepository.findAllByStudent_Id(anyLong()))
                .thenReturn(allGrades
                        .stream()
                        .filter(grade -> grade.getStudent().getId().equals(ID))
                        .collect(Collectors.toList()));

        List<Grade> returnedGrades = service.findAllByStudentId(ID);

        assertEquals(2, returnedGrades.size());

    }
}