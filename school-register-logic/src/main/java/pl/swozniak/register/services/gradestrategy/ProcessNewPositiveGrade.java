package pl.swozniak.register.services.gradestrategy;

import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.GradeDTO;

@Component
public class ProcessNewPositiveGrade implements ProcessNewGradeStrategy {

    @Override
    public GradeDTO processNewGrade(GradeDTO grade) {
        return grade;
    }
}
