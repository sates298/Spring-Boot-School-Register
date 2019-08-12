package pl.swozniak.register.mappers;

import org.mapstruct.Mapper;
import pl.swozniak.register.dtos.SchoolClassDTO;
import pl.swozniak.register.model.SchoolClass;

@Mapper(componentModel = "spring",uses = {EnumMapper.class, StudentMapper.class})
public interface SchoolClassMapper {
    SchoolClassDTO schoolClassToSchoolClassDTO(SchoolClass schoolClass);
}
