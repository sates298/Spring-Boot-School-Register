package pl.swozniak.register.backgroundjob;

import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.StudentDTO;

import java.util.List;

@Component
public class BackgroundJobTemplate {
    private final BackgroundJobStudentFinder studentFinder;
    private final BackgroundJobMailSender mailSender;

    public BackgroundJobTemplate(BackgroundJobStudentFinder studentFinder, BackgroundJobMailSender mailSender) {
        this.studentFinder = studentFinder;
        this.mailSender = mailSender;
    }

    public void executeJob(){
        List<StudentDTO> students = studentFinder.findAllStudentsWithOnes();
        mailSender.sendMails(students);
    }
}
