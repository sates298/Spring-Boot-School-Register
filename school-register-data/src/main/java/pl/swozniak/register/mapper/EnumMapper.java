package pl.swozniak.register.mapper;

import org.mapstruct.MapperConfig;

@MapperConfig
public class EnumMapper {
    protected String mapEnum(Enum<?> e) {
        if(e == null) return null;
        return e.toString();
    }
}
