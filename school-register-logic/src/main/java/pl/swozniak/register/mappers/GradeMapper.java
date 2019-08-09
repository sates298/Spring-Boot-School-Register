package pl.swozniak.register.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.model.Grade;


@Mapper(componentModel = "spring", uses = {EnumMapper.class})
public interface GradeMapper {

    @Mappings({
            @Mapping(target = "student.grades", ignore = true),
            @Mapping(target = "student.schoolClass", ignore = true),
    })
    GradeDTO gradeToGradeDTO(Grade grade);
}
