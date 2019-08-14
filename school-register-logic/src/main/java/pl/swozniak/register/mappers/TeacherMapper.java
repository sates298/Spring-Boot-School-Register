package pl.swozniak.register.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.swozniak.register.dtos.TeacherDTO;
import pl.swozniak.register.model.Teacher;

@Mapper(componentModel = "spring", uses = EnumMapper.class)
public interface TeacherMapper {

    @Mapping(target = "subject.teachers", ignore = true)
    TeacherDTO teacherToTeacherDTO(Teacher teacher);
}
