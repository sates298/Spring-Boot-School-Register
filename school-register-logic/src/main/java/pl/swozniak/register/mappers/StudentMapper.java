package pl.swozniak.register.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.Student;

@Mapper(componentModel = "spring", uses = {EnumMapper.class, GradeMapper.class})
public interface StudentMapper {

    @Mappings({
            @Mapping(target = "schoolClass.students", ignore = true),
            @Mapping(target = "parent.children", ignore = true)
    })
    StudentDTO studentToStudentDTO(Student student);

    @Mappings({
            @Mapping(target = "schoolClass.students", ignore = true),
            @Mapping(target = "parent.children", ignore = true)
    })
    Student studentDTOToStudent(StudentDTO studentDTO);
}
