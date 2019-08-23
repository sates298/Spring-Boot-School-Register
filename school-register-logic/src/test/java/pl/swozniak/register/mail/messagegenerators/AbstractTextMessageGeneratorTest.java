package pl.swozniak.register.mail.messagegenerators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.exceptions.NullPointerInTextContentException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AbstractTextMessageGeneratorTest {

    public static final String STUDENT_FIRST_NAME = "studentFirstName";
    public static final String LAST_NAME = "lastName";
    public static final String FIRST_NAME = "firstName";
    public static final String STUDENT_LAST_NAME = "studentLastName";

    @Mock
    AbstractTextMessageGenerator messageGenerator;


    @Test
    void generateIntroduction() {
        when(messageGenerator.generateIntroduction(any(), any())).thenCallRealMethod();

        StringBuilder returned = messageGenerator.generateIntroduction(FIRST_NAME, LAST_NAME);

        StringBuilder expected = new StringBuilder().
                append("Dear ").append(FIRST_NAME).append(" ").append(LAST_NAME).append("!")
                .append('\n');

        assertNotNull(returned);
        assertEquals(expected.toString(), returned.toString());
    }

    @Test
    void generateIntroductionThrowNullException() {
        when(messageGenerator.generateIntroduction(any(), any())).thenCallRealMethod();

        testGenerateIntroductionThrows(null, null);
        testGenerateIntroductionThrows(null,LAST_NAME);
        testGenerateIntroductionThrows(FIRST_NAME, null);
    }

    private void testGenerateIntroductionThrows(String afn, String aln) {
        assertThrows(NullPointerInTextContentException.class, () -> messageGenerator.generateIntroduction(afn, aln));
    }

    @Test
    void generateEnding() {
        when(messageGenerator.generateEnding(any())).thenCallRealMethod();

        StringBuilder current = new StringBuilder();
        StringBuilder returned = messageGenerator.generateEnding(current);

        String expected =
                '\n' +
                "Yours faithfully" +
                        '\n' +
                        "School XYZ";

        assertNotNull(returned);
        assertEquals(expected, returned.toString());
    }
}