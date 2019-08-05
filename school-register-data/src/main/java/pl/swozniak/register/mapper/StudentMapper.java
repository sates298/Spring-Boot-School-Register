package pl.swozniak.register.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.Student;

@Mapper(componentModel = "spring",uses = EnumMapper.class)
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDTO studentToStudentDTO(Student student);
}
