package pl.swozniak.register.mapper;

import org.mapstruct.MapperConfig;
import org.springframework.stereotype.Component;

@MapperConfig(componentModel = "spring")
@Component
public class EnumMapper {
    protected String mapEnum(Enum<?> e) {
        if(e == null) return null;
        return e.toString();
    }
}
