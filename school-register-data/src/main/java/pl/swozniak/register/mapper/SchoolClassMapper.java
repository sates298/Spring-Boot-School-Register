package pl.swozniak.register.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;
import pl.swozniak.register.dtos.SchoolClassDTO;
import pl.swozniak.register.model.SchoolClass;

@Mapper(componentModel = "spring",uses = EnumMapper.class)
public interface SchoolClassMapper {

    SchoolClassDTO schoolClassToSchoolClassDTO(SchoolClass schoolClass);
}
