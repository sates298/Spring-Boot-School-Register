package pl.swozniak.register.gradeprocessing.aftersaveprocessing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.gradeprocessing.aftersaveprocessing.PositiveProcessNewGradeStrategy;

import static org.junit.jupiter.api.Assertions.*;

class PositiveProcessNewGradeStrategyTest {

    public static final long ID = 1L;
    PositiveProcessNewGradeStrategy positiveGradeStrategy;

    GradeDTO grade;

    @BeforeEach
    void setUp() {
        positiveGradeStrategy = new PositiveProcessNewGradeStrategy();
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