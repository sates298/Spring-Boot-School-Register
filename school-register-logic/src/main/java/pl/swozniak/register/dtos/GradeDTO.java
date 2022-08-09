package pl.swozniak.register.dtos;

import lombok.Data;
import pl.swozniak.register.exceptions.ResourceNotFoundException;

@Data
public class GradeDTO {
    private Long id;
    private String grade;
    private SubjectDTO subject;
    private String notes;
    private StudentDTO student;

    public Long getOwnerId(){
        if(student == null) throw new ResourceNotFoundException();
        return student.getId();
    }
}
