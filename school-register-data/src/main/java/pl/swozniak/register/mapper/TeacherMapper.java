package pl.swozniak.register.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.swozniak.register.dtos.TeacherDTO;
import pl.swozniak.register.model.Teacher;

@Mapper(componentModel = "spring",uses = EnumMapper.class)
public interface TeacherMapper {

    TeacherDTO teacherToTeacherDTO(Teacher teacher);
}
