package pl.swozniak.register.services.gradestrategy;

import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.dtos.StudentDTO;

public class NewGradeBadBehavior implements NewGradeStrategy {

    @Override
    public GradeDTO checkNewGrade(GradeDTO grade) {
        //todo send mail to parent

        return grade;
    }

    private void sendMail(ParentDTO parent, StudentDTO student){

    }
}
