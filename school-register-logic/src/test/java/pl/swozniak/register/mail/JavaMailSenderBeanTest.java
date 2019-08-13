package pl.swozniak.register.mail;


import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.swozniak.register.BeanConfiguration;

import javax.mail.Message;
import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BeanConfiguration.class)
public class JavaMailSenderBeanTest {

    @Autowired
    JavaMailSender emailSender;

    private GreenMail testSmtp;

    @BeforeEach
    void setUp() {
        testSmtp = new GreenMail(ServerSetupTest.SMTP);
        testSmtp.start();

        ((JavaMailSenderImpl)emailSender).setPort(3025);
        ((JavaMailSenderImpl)emailSender).setHost("localhost");
    }

    @Test
    void testEmail() throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("test@sender.com");
        message.setTo("test@receiver.com");
        message.setSubject("test subject");
        message.setText("test message");
        emailSender.send(message);

        Message[] messages = testSmtp.getReceivedMessages();
        assertEquals(1, messages.length);
        assertEquals("test subject", messages[0].getSubject());
        String body = GreenMailUtil.getBody(messages[0]).replaceAll("=\r?\n", "");
        assertEquals("test message", body);
    }

    @AfterEach
    void tearDown() {
        testSmtp.stop();
    }
}
