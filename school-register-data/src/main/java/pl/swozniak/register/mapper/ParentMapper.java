package pl.swozniak.register.mapper;

import org.mapstruct.Mapper;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.model.Parent;

@Mapper(componentModel = "spring")
public interface ParentMapper {

    ParentDTO parentToParentDTO(Parent parent);
}
