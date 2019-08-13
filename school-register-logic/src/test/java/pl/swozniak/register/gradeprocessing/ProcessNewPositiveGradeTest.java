package pl.swozniak.register.gradeprocessing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.gradeprocessing.ProcessNewPositiveGrade;

import static org.junit.jupiter.api.Assertions.*;

class ProcessNewPositiveGradeTest {

    public static final long ID = 1L;
    ProcessNewPositiveGrade positiveGradeStrategy;

    GradeDTO grade;

    @BeforeEach
    void setUp() {
        positiveGradeStrategy = new ProcessNewPositiveGrade();
        grade = new GradeDTO();
        grade.setId(ID);
    }

    @Test
    void processNewGrade() {
        GradeDTO dto = positiveGradeStrategy.processNewGrade(grade);

        assertNotNull(dto);
        assertEquals(Long.valueOf(ID), dto.getId());
    }
}