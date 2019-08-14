package pl.swozniak.register.dtos;

import lombok.Data;

import java.util.List;

@Data
public class SubjectDTO {
    private Long id;
    private String name;
    private List<TeacherDTO> teachers;
}
