package pl.swozniak.register.mapper;

import org.mapstruct.Mapper;

import pl.swozniak.register.dtos.SchoolClassDTO;
import pl.swozniak.register.model.SchoolClass;

@Mapper(componentModel = "spring",uses = {EnumMapper.class, ParentMapperImpl.class})
public interface SchoolClassMapper {

    SchoolClassDTO schoolClassToSchoolClassDTO(SchoolClass schoolClass);
}
