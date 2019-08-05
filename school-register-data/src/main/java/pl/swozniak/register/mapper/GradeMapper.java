package pl.swozniak.register.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


import org.mapstruct.factory.Mappers;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.model.Grade;

@Mapper(componentModel = "spring", uses = EnumMapper.class)
public interface GradeMapper {

    GradeDTO gradeToGradeDTO(Grade grade);
}
