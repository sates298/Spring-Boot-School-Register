package pl.swozniak.register.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.Student;

@Mapper(componentModel = "spring", uses = {EnumMapper.class, GradeMapper.class})
public interface StudentMapper {

    @Mapping(target = "schoolClass.students", ignore = true)
    StudentDTO studentToStudentDTO(Student student);
}
