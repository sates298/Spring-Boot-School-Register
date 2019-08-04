package pl.swozniak.register.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.swozniak.register.dtos.SubjectDTO;
import pl.swozniak.register.model.Subject;

@Mapper
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    SubjectDTO subjectToSubjectDTO(Subject subject);

}
