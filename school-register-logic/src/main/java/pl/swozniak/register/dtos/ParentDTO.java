package pl.swozniak.register.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ParentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private List<StudentDTO> children;
}
