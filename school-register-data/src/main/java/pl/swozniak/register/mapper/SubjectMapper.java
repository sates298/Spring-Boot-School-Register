package pl.swozniak.register.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;
import pl.swozniak.register.dtos.SubjectDTO;
import pl.swozniak.register.model.Subject;

@Mapper(componentModel = "spring",uses = EnumMapper.class)
public interface SubjectMapper {

    SubjectDTO subjectToSubjectDTO(Subject subject);

}
