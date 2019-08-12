package pl.swozniak.register.dtos;

import lombok.Data;

@Data
public class GradeDTO {
    private Long id;
    private String grade;
    private SubjectDTO subject;
    private String notes;
    private StudentDTO student;
}
