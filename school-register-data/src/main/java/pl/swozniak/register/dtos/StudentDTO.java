package pl.swozniak.register.dtos;

import lombok.Data;


@Data
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private SchoolClassDTO schoolClass;
    private ParentDTO parent;
}
