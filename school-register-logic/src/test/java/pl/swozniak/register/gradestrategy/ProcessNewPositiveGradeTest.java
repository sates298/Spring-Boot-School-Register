package pl.swozniak.register.gradestrategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pl.swozniak.register.dtos.GradeDTO;

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