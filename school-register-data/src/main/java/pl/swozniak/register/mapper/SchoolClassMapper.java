package pl.swozniak.register.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.swozniak.register.dtos.SchoolClassDTO;
import pl.swozniak.register.model.SchoolClass;

@Mapper
public interface SchoolClassMapper {
    SchoolClassMapper INSTANCE = Mappers.getMapper(SchoolClassMapper.class);

    SchoolClassDTO schoolClassToSchoolClassDTO(SchoolClass schoolClass);
}