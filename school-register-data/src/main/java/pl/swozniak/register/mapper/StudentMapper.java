package pl.swozniak.register.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.Student;

@Mapper(componentModel = "spring",uses = {ParentMapperImpl.class, SchoolClassMapperImpl.class})
public interface StudentMapper {
    StudentDTO studentToStudentDTO(Student student);
}
