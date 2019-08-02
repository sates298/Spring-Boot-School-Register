package pl.swozniak.register.dtos;

import lombok.Data;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.model.Subject;
import pl.swozniak.register.model.enums.GradeValue;

@Data
public class GradeDTO {
    private GradeValue grade;
    private Subject subject;
    private String notes;
    private Student student;
}
