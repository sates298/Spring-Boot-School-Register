package pl.swozniak.register.mail.messagegenerators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.mail.messagegenerators.BadBehaviorMailMessageGenerator;

import static org.junit.jupiter.api.Assertions.*;

class BadBehaviorMailMessageGeneratorTest {

    public static final String STUDENT_FIRST_NAME = "studentFirstName";
    public static final String LAST_NAME = "lastName";
    public static final String FIRST_NAME = "firstName";

    private BadBehaviorMailMessageGenerator messageGenerator;
    private ParentDTO parentDTO;
    private String studentFirstName;
    private String notes;
    private StringBuilder builder;

    @BeforeEach
    void setUp() {
        parentDTO = new ParentDTO();
        parentDTO.setFirstName(FIRST_NAME);
        parentDTO.setLastName(LAST_NAME);
        studentFirstName = STUDENT_FIRST_NAME;

        messageGenerator = new BadBehaviorMailMessageGenerator();
        builder = new StringBuilder();
        setMessageBeginning();
    }

    @Test
    void generateMessageWithNotes() {
        notes = "notes";
        setMessageMiddleNotes();
        setMessageEnding();

        String finalMessage = messageGenerator.generateMessage(parentDTO, studentFirstName, notes);

        assertNotNull(finalMessage);
        assertEquals(builder.toString(), finalMessage);

    }

    @Test
    void generateMessageWithNullNotes() {
        notes = null;
        setMessageEnding();

        String finalMessage = messageGenerator.generateMessage(parentDTO, studentFirstName, notes);

        assertNotNull(finalMessage);
        assertEquals(builder.toString(), finalMessage);
    }

    @Test
    void generateMessageWithEmptyNotes() {
        notes = "";
        setMessageEnding();

        String finalMessage = messageGenerator.generateMessage(parentDTO, studentFirstName, notes);

        assertNotNull(finalMessage);
        assertEquals(builder.toString(), finalMessage);
    }

    @Test
    void generateMessageWithBlankNotes() {
        notes = "       ";
        setMessageEnding();

        String finalMessage = messageGenerator.generateMessage(parentDTO, studentFirstName, notes);

        assertNotNull(finalMessage);
        assertEquals(builder.toString(), finalMessage);
    }


    private void setMessageBeginning(){
        builder.append("Dear ").append(FIRST_NAME).append(" ").append(LAST_NAME)
                .append('\n')
                .append("Your child, ").append(studentFirstName).append(", get a negative grade from behavior.")
                .append('\n');
    }

    private void setMessageMiddleNotes(){
        builder.append("Grade with notes: ").append(notes);
    }

    private void setMessageEnding(){
        builder.append("Yours faithfully")
                .append('\n')
                .append("School XYZ");
    }

}