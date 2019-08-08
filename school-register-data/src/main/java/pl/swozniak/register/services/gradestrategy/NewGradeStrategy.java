package pl.swozniak.register.services.gradestrategy;

import pl.swozniak.register.dtos.GradeDTO;

public interface NewGradeStrategy {
    GradeDTO checkNewGrade(GradeDTO grade);
}
