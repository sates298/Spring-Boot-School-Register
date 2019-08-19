package pl.swozniak.register.gradeprocessing.updater;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.exceptions.IllegalOperationException;
import pl.swozniak.register.exceptions.ResourceNotFoundException;
import pl.swozniak.register.gradeprocessing.updater.GradeUpdater;
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
    private StudentDTO studentDTO;

    @BeforeEach
    void setUp() {
        updater = new GradeUpdater();

        studentDTO = new StudentDTO();
        studentDTO.setId(ID);

        returned = new GradeDTO();
        returned.setId(ID);
        returned.setNotes(DEFAULT_NOTES);
        returned.setGrade(DEFAULT_GRADE_VALUE.toString());
        returned.setStudent(studentDTO);
    }

    @Test
    void updateGradeNoChangesFill() {
        GradeDTO update = new GradeDTO();
        update.setId(ID);
        update.setNotes(DEFAULT_NOTES);
        update.setGrade(DEFAULT_GRADE_VALUE.toString());
        update.setStudent(studentDTO);

        GradeDTO updated = updater.updateGrade(returned, update);

        assertNotNull(updated);
        assertEquals(ID, updated.getId());
        assertEquals(DEFAULT_NOTES, updated.getNotes());
        assertEquals(DEFAULT_GRADE_VALUE.toString(), updated.getGrade());
        assertEquals(studentDTO, updated.getStudent());
    }

    @Test
    void updateGradeNoChangesNulls(){
        GradeDTO update = new GradeDTO();

        GradeDTO updated = updater.updateGrade(returned, update);

        assertNotNull(updated);
        assertEquals(ID, updated.getId());
        assertEquals(DEFAULT_NOTES, updated.getNotes());
        assertEquals(DEFAULT_GRADE_VALUE.toString(), updated.getGrade());
        assertEquals(studentDTO, updated.getStudent());
    }

    @Test
    void updateGradeDiffValue() {
        GradeValue value = GradeValue.ONE;
        GradeDTO update = new GradeDTO();
        update.setGrade(value.toString());

        GradeDTO updated = updater.updateGrade(returned, update);

        assertNotNull(updated);
        assertEquals(ID, updated.getId());
        assertEquals(DEFAULT_NOTES, updated.getNotes());
        assertEquals(value.toString(), updated.getGrade());
        assertEquals(studentDTO, updated.getStudent());

    }

    @Test
    void updateGradeDiffNotes() {
        String notes = "not" + DEFAULT_NOTES;
        GradeDTO update = new GradeDTO();
        update.setNotes(notes);

        GradeDTO updated = updater.updateGrade(returned, update);

        assertNotNull(updated);
        assertEquals(ID, updated.getId());
        assertEquals(notes, updated.getNotes());
        assertEquals(DEFAULT_GRADE_VALUE.toString(), updated.getGrade());
        assertEquals(studentDTO, updated.getStudent());
    }

    @Test
    void updateGradeDiffNotesAndValue(){
        String notes = "not" + DEFAULT_NOTES;
        GradeValue value = GradeValue.ONE;
        GradeDTO update = new GradeDTO();
        update.setNotes(notes);
        update.setGrade(value.toString());

        GradeDTO updated = updater.updateGrade(returned, update);

        assertNotNull(updated);
        assertEquals(ID, updated.getId());
        assertEquals(notes, updated.getNotes());
        assertEquals(value.toString(), updated.getGrade());
        assertEquals(studentDTO, updated.getStudent());
    }

    @Test
    void updateGradeOwnerNotFound() {
        returned.setStudent(null);

        assertThrows(ResourceNotFoundException.class,
                ()-> updater.updateGrade(returned, new GradeDTO())
        );
    }

    @Test
    void updateGradeDiffOwners() {
        StudentDTO studentDTO2 = new StudentDTO();
        studentDTO2.setId(ID + 1);

        GradeDTO update = new GradeDTO();
        update.setStudent(studentDTO2);


        assertThrows(IllegalOperationException.class,
                ()-> updater.updateGrade(returned, update)
        );
    }
}