package pl.swozniak.register.mapper;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.model.Grade;

@Mapper(componentModel = "spring", uses = {EnumMapper.class, SubjectMapperImpl.class, StudentMapperImpl.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GradeMapper {

    GradeDTO gradeToGradeDTO(Grade grade);
}
