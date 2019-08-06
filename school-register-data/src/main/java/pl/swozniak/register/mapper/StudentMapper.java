package pl.swozniak.register.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.Student;

@Mapper(componentModel = "spring",uses = {ParentMapperImpl.class, SchoolClassMapperImpl.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StudentMapper {

    StudentDTO studentToStudentDTO(Student student);
}
