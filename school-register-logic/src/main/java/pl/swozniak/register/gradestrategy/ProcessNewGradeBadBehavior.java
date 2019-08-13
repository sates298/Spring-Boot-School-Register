package pl.swozniak.register.gradestrategy;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.dtos.StudentDTO;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class ProcessNewGradeBadBehavior implements ProcessNewGradeStrategy {

    private final JavaMailSender mailSender;
    private final Environment environment;
    private final BadBehaviorMailMessageGenerator messageGenerator;

    public static int noOfQuickServiceThreads = 20;

    private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads);

    public ProcessNewGradeBadBehavior(JavaMailSender mailSender, Environment environment,
                                      BadBehaviorMailMessageGenerator messageGenerator) {
        this.mailSender = mailSender;
        this.environment = environment;
        this.messageGenerator = messageGenerator;
    }


    @Override
    public GradeDTO processNewGrade(GradeDTO grade) {
        StudentDTO student = grade.getStudent();
        sendMail(student.getParent(), student, grade.getNotes());
        return grade;
    }

    private void sendMail(ParentDTO parent, StudentDTO student, String notes) {
        String message = messageGenerator.generateMessage(parent, student.getFirstName(), notes);

        SimpleMailMessage mail = generateMail(parent.getEmail(), message);

        quickService.submit(() -> mailSender.send(mail));
    }

    private SimpleMailMessage generateMail(String mailTo, String message){
        SimpleMailMessage mail= new SimpleMailMessage();
        mail.setFrom(environment.getProperty("spring.mail.username"));
        mail.setTo(mailTo);
        mail.setSubject("Bad Behavior");
        mail.setText(message);

        return mail;
    }


}
