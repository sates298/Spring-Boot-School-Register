package pl.swozniak.register.gradeprocessing.counters;

import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.model.enums.GradeValue;

import java.util.List;

@Component
public class GradesAverageCounter {

    public Double countAverage(List<GradeDTO> grades){
        Double sum = 0.0;
        for (GradeDTO grade: grades){
            sum+=GradeValue.fromString(grade.getGrade()).getValue();
        }
        return grades.size() > 0 ? sum/grades.size() : 0.0;
    }
}
