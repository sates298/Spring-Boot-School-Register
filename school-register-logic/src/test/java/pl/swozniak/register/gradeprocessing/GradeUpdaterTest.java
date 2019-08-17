package pl.swozniak.register.gradeprocessing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.exceptions.ResourceNotFoundException;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.model.enums.GradeValue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GradeUpdaterTest {

    public static final Long ID = 1L;
    public static final String DEFAULT_NOTES = "notes";
    public static final GradeValue DEFAULT_GRADE_VALUE = GradeValue.FIVE;

    private GradeUpdater updater;
    private GradeDTO returned;

    @BeforeEach
    void setUp() {
        updater = new GradeUpdater();
        returned = new GradeDTO();
        returned.setId(ID);
        returned.setNotes(DEFAULT_NOTES);
        returned.setGrade(DEFAULT_GRADE_VALUE.toString());
    }

    @Test
    void updateGradeNoChanges() {
    }

    @Test
    void updateGradeDiffValue(){

    }

    @Test
    void updateGradeDiffNotes(){

    }

    @Test
    void updateGradeDiffStudent(){

    }

    @Test
    void updateGradeOwnerNotFound(){

    }
}