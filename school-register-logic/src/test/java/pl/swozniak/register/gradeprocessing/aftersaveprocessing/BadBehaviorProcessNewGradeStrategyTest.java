package pl.swozniak.register.gradeprocessing.aftersaveprocessing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import pl.swozniak.register.mail.messagegenerators.BadBehaviorMailMessageGenerator;


@ExtendWith(MockitoExtension.class)
class BadBehaviorProcessNewGradeStrategyTest {

    @Mock
    JavaMailSender mailSender;

    @Mock
    Environment environment;

    @Mock
    BadBehaviorMailMessageGenerator messageGenerator;


    @BeforeEach
    void setUp() {
    }

    @Test
    void processNewGrade() {
    }
}