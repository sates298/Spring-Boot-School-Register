package pl.swozniak.register.gradeprocessing.aftersaveprocessing;

import pl.swozniak.register.dtos.GradeDTO;

public interface ProcessNewGradeStrategy {
    GradeDTO processNewGrade(GradeDTO grade);
}
