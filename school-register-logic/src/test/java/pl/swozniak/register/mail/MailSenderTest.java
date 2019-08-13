package pl.swozniak.register.mail;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class MailSenderTest {

    private static final String TEXT = "text";
    private static final String TO = "to@you.com";
    private static final String SUBJECT = "subject";

    @Mock
    JavaMailSender jms;

    @InjectMocks
    MailSender mailSender;


    @Test
    void sendMail() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailSender.sendMail(mailMessage);

        await().atMost(100, MILLISECONDS);

        verify(jms).send(any(SimpleMailMessage.class));
    }

    @Test
    void generateMail() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText(TEXT);
        mailMessage.setTo(TO);
        mailMessage.setSubject(SUBJECT);

        SimpleMailMessage returned = mailSender.generateMail(TO, SUBJECT, TEXT);

        assertNotNull(returned);

        assertEquals(mailMessage, returned);
    }
}