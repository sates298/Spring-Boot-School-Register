package pl.swozniak.register.dtos;

import lombok.Data;

@Data
public class ParentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
}
