package pl.swozniak.register.dtos;

import lombok.Data;

import pl.swozniak.register.model.enums.GradeValue;

@Data
public class GradeDTO {
    private GradeValue grade;
    private SubjectDTO subject;
    private String notes;
    private StudentDTO student;
}
