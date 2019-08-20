package pl.swozniak.register.backgroundjob;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.mail.MailSender;
import pl.swozniak.register.mail.messagegenerators.BackgroundJobMailMessageGenerator;

import java.util.List;

@Component
public class BackgroundJobMailSender {

    private final MailSender mailSender;
    private final BackgroundJobMailMessageGenerator messageGenerator;

    public BackgroundJobMailSender(MailSender mailSender, BackgroundJobMailMessageGenerator messageGenerator) {
        this.mailSender = mailSender;
        this.messageGenerator = messageGenerator;
    }

    public void sendMails(List<StudentDTO> students){
        students.forEach(this::sendOneMail);
    }

    private void sendOneMail(StudentDTO student){
        String message = messageGenerator.generateMessage();
        String mailTo = student.getParent().getEmail();
        SimpleMailMessage mailMessage = mailSender.generateMail(mailTo, "Bad grades", message);
        mailSender.sendMail(mailMessage);
    }
}
