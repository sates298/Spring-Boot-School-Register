package pl.swozniak.register.services.gradestrategy;

import pl.swozniak.register.dtos.GradeDTO;

public interface ProcessNewGradeStrategy {
    GradeDTO processNewGrade(GradeDTO grade);
}
