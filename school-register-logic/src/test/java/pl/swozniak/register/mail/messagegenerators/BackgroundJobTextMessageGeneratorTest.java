package pl.swozniak.register.mail.messagegenerators;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.swozniak.register.backgroundjob.BackgroundJobStudentFinder;
import pl.swozniak.register.exceptions.NullPointerInTextContentException;

import static org.junit.jupiter.api.Assertions.*;
import static pl.swozniak.register.mail.messagegenerators.AbstractTextMessageGeneratorTest.*;

class BackgroundJobTextMessageGeneratorTest {

    public static final String AVERAGE = "average";
    private BackgroundJobTextMessageGenerator messageGenerator;
    private StringBuilder expectedBuilder;
    private TextMessageContent content;

    @BeforeEach
    void setUp() {
        content = TextMessageContent.builder()
                .addresseeFirstName(FIRST_NAME)
                .addresseeLastName(LAST_NAME)
                .studentFirstName(STUDENT_FIRST_NAME)
                .studentLastName(STUDENT_LAST_NAME)
                .notes(AVERAGE)
                .build();

        messageGenerator = new BackgroundJobTextMessageGenerator();
        expectedBuilder = new StringBuilder();
        setExpectedMessageBeginning();
    }

    @Test
    void generateTextMessageNullContent() {
        assertThrows(NullPointerInTextContentException.class, () ->
                messageGenerator.generateTextMessage(null));
    }

    @Test
    void generateTextMessageWithNullInContent() {
        testThrowingNullInContentException(STUDENT_FIRST_NAME, STUDENT_LAST_NAME, null);
        testThrowingNullInContentException(STUDENT_FIRST_NAME, null, AVERAGE);
        testThrowingNullInContentException(null, STUDENT_LAST_NAME, AVERAGE);
        testThrowingNullInContentException(null, null, null);
    }

    private void testThrowingNullInContentException(String sfn, String sln, String avg) {
        content.setStudentFirstName(sfn);
        content.setStudentLastName(sln);
        content.setNotes(avg);

        assertThrows(NullPointerInTextContentException.class, () ->
                messageGenerator.generateTextMessage(content));
    }

    @Test
    void generateTextMessageFull() {
        setExpectedMessageEnding();

        String returned = messageGenerator.generateTextMessage(content);

        assertNotNull(returned);
        assertEquals(expectedBuilder.toString(), returned);
    }

    private void setExpectedMessageBeginning() {
        expectedBuilder.append("Dear ").append(FIRST_NAME).append(" ").append(LAST_NAME).append('!')
                .append('\n')
                .append("Your child, ")
                .append(STUDENT_FIRST_NAME)
                .append(" ")
                .append(STUDENT_LAST_NAME)
                .append(", has at least")
                .append(BackgroundJobStudentFinder.NUMBER_OF_CORRECT_GRADES.toString())
                .append(" grades ")
                .append(BackgroundJobStudentFinder.GRADE_VALUE.toString())
                .append(".\n");

        expectedBuilder.append("His\\Her grades average is equal ")
                .append(AVERAGE)
                .append(".\n");
    }

    private void setExpectedMessageEnding() {
        expectedBuilder.append('\n')
                .append("Yours faithfully")
                .append('\n')
                .append("School XYZ");
    }
}