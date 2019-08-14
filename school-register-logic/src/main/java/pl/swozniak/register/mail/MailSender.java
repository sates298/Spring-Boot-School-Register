package pl.swozniak.register.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class MailSender {

    private final JavaMailSender jms;

    private static int noOfQuickServiceThreads = 20;

    private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads);

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
