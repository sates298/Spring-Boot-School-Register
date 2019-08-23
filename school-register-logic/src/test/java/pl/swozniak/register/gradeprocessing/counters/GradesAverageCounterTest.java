package pl.swozniak.register.gradeprocessing.counters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.model.enums.GradeValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradesAverageCounterTest {

    private GradesAverageCounter counter;

    @BeforeEach
    void setUp() {
        counter = new GradesAverageCounter();
    }

    @Test
    void calculateAverage() {
        GradeDTO gradeDTO = new GradeDTO();
        gradeDTO.setGrade(GradeValue.THREE.toString());

        GradeDTO gradeDTO1 = new GradeDTO();
        gradeDTO1.setGrade(GradeValue.FOUR.toString());

        GradeDTO gradeDTO2 = new GradeDTO();
        gradeDTO2.setGrade(GradeValue.SIX.toString());

        List<GradeDTO> gradeDTOS = Arrays.asList(gradeDTO, gradeDTO1, gradeDTO2);

        Double score = counter.calculateAverage(gradeDTOS);

        assertEquals(Double.valueOf((3.0 + 4.0 + 6.0)/3), score);
    }

    @Test
    void calculateAverageFromNull(){
        Double score = counter.calculateAverage(null);
        assertEquals(Double.valueOf(0), score);
    }

    @Test
    void calculateAverageFromEmptyList(){
        Double score = counter.calculateAverage(Collections.emptyList());
        assertEquals(Double.valueOf(0), score);
    }
}