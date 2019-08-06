package pl.swozniak.register.mapper;

import org.mapstruct.Mapper;
import pl.swozniak.register.dtos.TeacherDTO;
import pl.swozniak.register.model.Teacher;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherDTO teacherToTeacherDTO(Teacher teacher);
}
