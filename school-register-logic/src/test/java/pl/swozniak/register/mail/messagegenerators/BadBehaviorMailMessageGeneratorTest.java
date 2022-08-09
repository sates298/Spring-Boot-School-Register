package pl.swozniak.register.mail.messagegenerators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.swozniak.register.exceptions.NullPointerInTextContentException;

import static org.junit.jupiter.api.Assertions.*;
import static pl.swozniak.register.mail.messagegenerators.AbstractTextMessageGeneratorTest.*;

class BadBehaviorMailMessageGeneratorTest {


    private BadBehaviorTextMessageGenerator messageGenerator;
    private StringBuilder expectedBuilder;
    private TextMessageContent content;

    @BeforeEach
    void setUp() {

        content = TextMessageContent.builder()
                .addresseeFirstName(FIRST_NAME)
                .addresseeLastName(LAST_NAME)
                .studentFirstName(STUDENT_FIRST_NAME)
                .studentLastName(STUDENT_LAST_NAME)
                .build();

        messageGenerator = new BadBehaviorTextMessageGenerator();
        expectedBuilder = new StringBuilder();
        setExpectedMessageBeginning();
    }


    @Test
    void generateMessageWithNotes() {
        String notes = "notes";
        content.setNotes(notes);
        setExpectedMessageMiddleNotes(notes);
        setExpectedMessageEnding();

        String finalMessage = messageGenerator.generateTextMessage(content);

        assertNotNull(finalMessage);
        assertEquals(expectedBuilder.toString(), finalMessage);

    }

    @Test
    void generateMessageWithNullNotes() {
        content.setNotes(null);
        setExpectedMessageEnding();

        String finalMessage = messageGenerator.generateTextMessage(content);

        assertNotNull(finalMessage);
        assertEquals(expectedBuilder.toString(), finalMessage);
    }

    @Test
    void generateMessageWithEmptyNotes() {
        String notes = "";
        content.setNotes(notes);
        setExpectedMessageEnding();

        String finalMessage = messageGenerator.generateTextMessage(content);

        assertNotNull(finalMessage);
        assertEquals(expectedBuilder.toString(), finalMessage);
    }

    @Test
    void generateMessageWithBlankNotes() {
        String notes = "       ";
        content.setNotes(notes);
        setExpectedMessageEnding();

        String finalMessage = messageGenerator.generateTextMessage(content);

        assertNotNull(finalMessage);
        assertEquals(expectedBuilder.toString(), finalMessage);
    }


    private void setExpectedMessageBeginning() {
        expectedBuilder.append("Dear ").append(FIRST_NAME).append(" ").append(LAST_NAME).append('!')
                .append('\n')
                .append("Your child, ")
                .append(STUDENT_FIRST_NAME)
                .append(" ")
                .append(STUDENT_LAST_NAME)
                .append(", got a negative grade from behavior.")
                .append('\n');
    }

    private void setExpectedMessageMiddleNotes(String notes) {
        expectedBuilder.append("Grade with notes: ").append(notes);
    }

    private void setExpectedMessageEnding() {
        expectedBuilder.append('\n')
                .append("Yours faithfully")
                .append('\n')
                .append("School XYZ");
    }

    @Test
    void generateMessageWithNullContent() {
        assertThrows(NullPointerInTextContentException.class, () ->
                messageGenerator.generateTextMessage(null));
    }

    @Test
    void generateMessageStudentNamesNull() {
        testGenerateCommonMiddleWithNullArgument(null, STUDENT_LAST_NAME);
        testGenerateCommonMiddleWithNullArgument(STUDENT_FIRST_NAME, null);
        testGenerateCommonMiddleWithNullArgument(null, null);
    }

    private void testGenerateCommonMiddleWithNullArgument(String sfn, String sln) {
        content.setStudentFirstName(sfn);
        content.setStudentLastName(sln);

        assertThrows(NullPointerInTextContentException.class, () ->
        messageGenerator.generateTextMessage(content));
    }
}