package pl.swozniak.register.mappers;

import org.mapstruct.Mapper;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.model.Parent;

@Mapper(componentModel = "spring", uses = EnumMapper.class)
public interface ParentMapper {
    ParentDTO parentToParentDTO(Parent parent);
}
