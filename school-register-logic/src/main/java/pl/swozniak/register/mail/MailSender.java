package pl.swozniak.register.mail;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
@Slf4j
public class MailSender {
    private final JavaMailSender jms;

    private ExecutorService quickService = Executors.newCachedThreadPool();

    public MailSender(JavaMailSender jms) {
        this.jms = jms;
    }

    public void sendMail(SimpleMailMessage mailMessage){
        quickService.submit(() -> jms.send(mailMessage));
    }

    public SimpleMailMessage generateMail(String mailTo, String subject, String message){
        SimpleMailMessage mail= new SimpleMailMessage();
        mail.setTo(mailTo);
        mail.setSubject(subject);
        mail.setText(message);

        return mail;
    }
}
