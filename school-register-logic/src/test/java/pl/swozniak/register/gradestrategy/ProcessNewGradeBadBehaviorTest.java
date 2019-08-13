package pl.swozniak.register.gradestrategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;


@ExtendWith(MockitoExtension.class)
class ProcessNewGradeBadBehaviorTest {

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