package pl.swozniak.register.gradeprocessing;

import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.GradeDTO;

@Component
public class PositiveProcessNewGradeStrategy implements ProcessNewGradeStrategy {

    @Override
    public GradeDTO processNewGrade(GradeDTO grade) {
        return grade;
    }
}
