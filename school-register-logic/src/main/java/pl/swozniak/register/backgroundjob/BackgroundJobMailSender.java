package pl.swozniak.register.backgroundjob;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.gradeprocessing.counters.GradesAverageCounter;
import pl.swozniak.register.mail.MailSender;
import pl.swozniak.register.mail.messagegenerators.TextMessageContent;
import pl.swozniak.register.mail.messagegenerators.TextMessageGenerator;

import java.util.List;

@Component
public class BackgroundJobMailSender {

    public static final String MAIL_SUBJECT = "Bad grades";
    private final MailSender mailSender;
    private final TextMessageGenerator messageGenerator;
    private final GradesAverageCounter averageCounter;

    public BackgroundJobMailSender(MailSender mailSender, GradesAverageCounter averageCounter,
                                   @Qualifier("backgroundJobTextMessageGenerator")TextMessageGenerator messageGenerator){
        this.mailSender = mailSender;
        this.messageGenerator = messageGenerator;
        this.averageCounter = averageCounter;
    }

    public void sendMails(List<StudentDTO> students){
        students.forEach(this::sendOneMail);
    }

    private void sendOneMail(StudentDTO student){
        String message = messageGenerator.generateTextMessage(createMessageContent(student));

        String mailTo = student.getParent().getEmail();
        SimpleMailMessage mailMessage = mailSender.generateMail(mailTo, MAIL_SUBJECT, message);
        mailSender.sendMail(mailMessage);
    }

    private TextMessageContent createMessageContent(StudentDTO student){
        return messageGenerator.generateDefaultContent(
                student.getParent(),
                student,
                averageCounter
                        .countAverage(student.getGrades())
                        .toString()
        );
    }
}
