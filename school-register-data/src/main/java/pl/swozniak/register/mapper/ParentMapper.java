package pl.swozniak.register.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.model.Parent;

@Mapper(componentModel = "spring",uses = EnumMapper.class)
public interface ParentMapper {

    ParentDTO parentToParentDTO(Parent parent);
}
