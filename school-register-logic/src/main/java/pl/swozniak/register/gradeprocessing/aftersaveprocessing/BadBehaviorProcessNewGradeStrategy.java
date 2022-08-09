package pl.swozniak.register.gradeprocessing.aftersaveprocessing;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.mail.MailSender;
import pl.swozniak.register.mail.messagegenerators.BadBehaviorTextMessageGenerator;
import pl.swozniak.register.mail.messagegenerators.TextMessageContent;
import pl.swozniak.register.mail.messagegenerators.TextMessageGenerator;

@Component
public class BadBehaviorProcessNewGradeStrategy implements ProcessNewGradeStrategy {

    public static final String MAIL_SUBJECT = "Bad Behavior";

    private final MailSender mailSender;
    private final TextMessageGenerator messageGenerator;


    public BadBehaviorProcessNewGradeStrategy(MailSender mailSender,
                                              @Qualifier("badBehaviorTextMessageGenerator") TextMessageGenerator messageGenerator) {
        this.mailSender = mailSender;
        this.messageGenerator = messageGenerator;
    }


    @Override
    public GradeDTO processNewGrade(GradeDTO grade) {
        StudentDTO student = grade.getStudent();
        sendMail(student.getParent(), student, grade.getNotes());
        return grade;
    }

    private void sendMail(ParentDTO parent, StudentDTO student, String notes) {
        TextMessageContent content = messageGenerator.generateDefaultContent(parent, student, notes);
        String message = messageGenerator.generateTextMessage(content);

        SimpleMailMessage mail = mailSender.generateMail(parent.getEmail(), MAIL_SUBJECT, message);

        mailSender.sendMail(mail);
    }

}
