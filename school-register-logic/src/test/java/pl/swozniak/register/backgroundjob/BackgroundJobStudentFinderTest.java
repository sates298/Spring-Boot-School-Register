package pl.swozniak.register.backgroundjob;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.enums.GradeValue;
import pl.swozniak.register.services.interfaces.StudentService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static pl.swozniak.register.backgroundjob.BackgroundJobStudentFinder.GRADE_VALUE;
import static pl.swozniak.register.backgroundjob.BackgroundJobStudentFinder.NUMBER_OF_CORRECT_GRADES;

@ExtendWith(MockitoExtension.class)
class BackgroundJobStudentFinderTest {

    public static final String STRING = "wrong grade";
    @Mock
    StudentService studentService;

    @InjectMocks
    BackgroundJobStudentFinder studentFinder;

    private StudentDTO studentDTO;
    private GradeDTO gradeDTO;

    @BeforeEach
    void setUp() {
        gradeDTO = new GradeDTO();
        gradeDTO.setGrade(GRADE_VALUE.toString());

        List<GradeDTO> grades = new ArrayList<>();
        for (int i=0; i < NUMBER_OF_CORRECT_GRADES; i++){
            grades.add(gradeDTO);
        }

        GradeDTO gradeDTO1 = new GradeDTO();
        gradeDTO1.setGrade(STRING);
        grades.add(gradeDTO1);

        studentDTO = new StudentDTO();
        studentDTO.setGrades(grades);
    }

    @Test
    void findAllStudentsWithOnes() {
        StudentDTO studentDTO1 = new StudentDTO();
        studentDTO1.setGrades(Collections.emptyList());

        List<GradeDTO> grades = new ArrayList<>();
        for (int i=0; i < (NUMBER_OF_CORRECT_GRADES - 1); i++){
            grades.add(gradeDTO);
        }

        StudentDTO studentDTO2 = new StudentDTO();
        studentDTO2.setGrades(grades);

        List<StudentDTO> students = Arrays.asList(studentDTO,studentDTO1, studentDTO2, studentDTO);

        when(studentService.findAll()).thenReturn(students);

        List<StudentDTO> returned = studentFinder.findAllStudentsWithOnes();
        assertNotNull(returned);
        assertEquals(2, returned.size());

    }
}