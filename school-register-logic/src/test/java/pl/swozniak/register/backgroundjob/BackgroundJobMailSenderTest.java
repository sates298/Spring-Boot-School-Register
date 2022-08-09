package pl.swozniak.register.backgroundjob;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.exceptions.NullPointerWhileSendingMailException;
import pl.swozniak.register.gradeprocessing.counters.GradesAverageCounter;
import pl.swozniak.register.mail.MailSender;
import pl.swozniak.register.mail.messagegenerators.TextMessageContent;
import pl.swozniak.register.mail.messagegenerators.TextMessageGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BackgroundJobMailSenderTest {

    public static final String MAIL = "mail";
    @Mock
    MailSender mailSender;

    @Mock
    TextMessageGenerator messageGenerator;

    @Mock
    GradesAverageCounter averageCounter;

    @InjectMocks
    BackgroundJobMailSender jobMailSender;

    private StudentDTO studentDTO;

    @BeforeEach
    void setUp() {
        ParentDTO parentDTO = new ParentDTO();
        parentDTO.setEmail(MAIL);

        studentDTO = new StudentDTO();
        studentDTO.setParent(parentDTO);
        studentDTO.setGrades(Collections.emptyList());

    }

    @Test
    void sendMails() {
        List<StudentDTO> studentDTOS = Arrays.asList(studentDTO, studentDTO);
        jobMailSender.sendMails(studentDTOS);

        verify(averageCounter,times(2)).calculateAverage(anyList());
        verify(messageGenerator,times(2)).generateTextMessage(any());
        verify(messageGenerator,times(2)).generateDefaultContent(any(), any(), any());
        verify(mailSender, times(2)).generateMail(any(), any(), any());
        verify(mailSender, times(2)).sendMail(any());
    }

    @Test
    void sendMailToNull(){
        studentDTO.getParent().setEmail(null);
        testSendMailThrowingException();
        studentDTO.setParent(null);
        testSendMailThrowingException();
    }

    private void testSendMailThrowingException(){
        assertThrows(NullPointerWhileSendingMailException.class, () ->
                jobMailSender.sendMails(Collections.singletonList(studentDTO)));
    }
}