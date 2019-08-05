package pl.swozniak.register.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.model.Grade;

@Mapper
public interface GradeMapper {
    GradeMapper INSTANCE = Mappers.getMapper(GradeMapper.class);

    @Mapping(target = "grade", source = "entity.grade")
    GradeDTO gradeToGradeDTO(Grade grade);
}
