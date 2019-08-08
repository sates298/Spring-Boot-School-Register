package pl.swozniak.register.services.gradestrategy;

import pl.swozniak.register.dtos.GradeDTO;

public class NewPositiveGrade implements NewGradeStrategy {

    @Override
    public GradeDTO checkNewGrade(GradeDTO grade) {
        return grade;
    }
}
