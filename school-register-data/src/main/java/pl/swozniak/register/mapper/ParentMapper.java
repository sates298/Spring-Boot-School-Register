package pl.swozniak.register.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.model.Parent;

@Mapper
public interface ParentMapper {
    ParentMapper INSTANCE = Mappers.getMapper(ParentMapper.class);

    ParentDTO parentToParentDTO(Parent parent);
}
