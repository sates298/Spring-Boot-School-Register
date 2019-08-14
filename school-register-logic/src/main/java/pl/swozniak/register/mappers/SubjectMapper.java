package pl.swozniak.register.mappers;

import org.mapstruct.Mapper;
import pl.swozniak.register.dtos.SubjectDTO;
import pl.swozniak.register.model.Subject;

@Mapper(componentModel = "spring",uses = {EnumMapper.class, TeacherMapper.class})
public interface SubjectMapper {
    SubjectDTO subjectToSubjectDTO(Subject subject);

}
