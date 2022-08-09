package pl.swozniak.register.mail.messagegenerators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.exceptions.NullPointerInTextContentException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static pl.swozniak.register.mail.messagegenerators.AbstractTextMessageGeneratorTest.*;

@ExtendWith(MockitoExtension.class)
class TextMessageGeneratorTest {

    public static final String NOTES = "notes";
    @Mock
    TextMessageGenerator messageGenerator;

    private TextMessageContent expectedContent;
    private ParentDTO parentDTO;
    private StudentDTO studentDTO;

    @BeforeEach
    void setUp() {
        parentDTO = new ParentDTO();
        parentDTO.setFirstName(FIRST_NAME);
        parentDTO.setLastName(LAST_NAME);

        studentDTO = new StudentDTO();
        studentDTO.setFirstName(STUDENT_FIRST_NAME);
        studentDTO.setLastName(STUDENT_LAST_NAME);

        expectedContent = TextMessageContent.builder().addresseeFirstName(FIRST_NAME)
                .addresseeLastName(LAST_NAME)
                .studentFirstName(STUDENT_FIRST_NAME)
                .studentLastName(STUDENT_LAST_NAME)
                .notes(NOTES)
                .build();
    }

    @Test
    void generateDefaultContent() {
        when(messageGenerator.generateDefaultContent(any(), any(), anyString())).thenCallRealMethod();
        TextMessageContent returned = messageGenerator.generateDefaultContent(parentDTO, studentDTO, NOTES);

        assertNotNull(returned);
        assertEquals(expectedContent, returned);
    }

    @Test
    void generateDefaultContentWithNull(){
        when(messageGenerator.generateDefaultContent(any(), any(), anyString())).thenCallRealMethod();

        testGenerateDefaultContentThrowsException(null, studentDTO);
        testGenerateDefaultContentThrowsException(parentDTO, null);
        testGenerateDefaultContentThrowsException(null, null);
    }

    private void testGenerateDefaultContentThrowsException(ParentDTO parentDTO, StudentDTO studentDTO){
        assertThrows(NullPointerInTextContentException.class, () ->
                messageGenerator.generateDefaultContent(parentDTO, studentDTO, NOTES));
    }


}