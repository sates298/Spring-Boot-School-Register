package pl.swozniak.register.mapper;

import org.mapstruct.Mapper;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.Student;

@Mapper(componentModel = "spring", uses = EnumMapper.class)
public interface StudentMapper {
    StudentDTO studentToStudentDTO(Student student);
}
