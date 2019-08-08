package pl.swozniak.register.dtos;

import lombok.Data;

import java.util.List;


@Data
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private List<GradeDTO> grades;
    private SchoolClassDTO schoolClass;
    private ParentDTO parent;
}
