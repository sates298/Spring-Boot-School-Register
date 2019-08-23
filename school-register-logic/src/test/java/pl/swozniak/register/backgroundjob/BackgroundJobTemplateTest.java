package pl.swozniak.register.backgroundjob;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.dtos.StudentDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BackgroundJobTemplateTest {

    @Mock
    BackgroundJobStudentFinder studentFinder;

    @Mock
    BackgroundJobMailSender mailSender;

    @InjectMocks
    BackgroundJobTemplate template;

    @Test
    void executeJob() {
        template.executeJob();

        verify(studentFinder).findAllStudentsWithOnes();
        verify(mailSender).sendMails(any());
    }
}