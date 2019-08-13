package pl.swozniak.register.gradeprocessing;

import pl.swozniak.register.dtos.GradeDTO;

public interface ProcessNewGradeStrategy {
    GradeDTO processNewGrade(GradeDTO grade);
}
