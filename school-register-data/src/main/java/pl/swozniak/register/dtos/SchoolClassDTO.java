package pl.swozniak.register.dtos;

import lombok.Data;
import pl.swozniak.register.model.enums.ClassLevel;

import java.util.List;

@Data
public class SchoolClassDTO {
    private Long id;
    private Character character;
    private String level;
}
