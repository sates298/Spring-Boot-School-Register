package pl.swozniak.register.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.dtos.SchoolClassDTO;
import pl.swozniak.register.model.SchoolClass;
import pl.swozniak.register.model.enums.ClassLevel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SchoolClassMapperTest {

    public static final Long ID = 1L;
    public static final Character CHARACTER = 'A';
    public static final ClassLevel CLASS_LEVEL = ClassLevel.THIRD;
    @Mock
    EnumMapper enumMapper;

    @InjectMocks
    SchoolClassMapper schoolClassMapper = Mappers.getMapper(SchoolClassMapper.class);

    @Test
    void schoolClassToSchoolClassDTO() {
        when(enumMapper.mapEnum(any())).thenCallRealMethod();

        SchoolClass schoolClass = SchoolClass.builder()
                .id(ID)
                .character(CHARACTER)
                .level(CLASS_LEVEL)
                .build();

        SchoolClassDTO dto = schoolClassMapper.schoolClassToSchoolClassDTO(schoolClass);

        assertNotNull(dto);

        assertEquals(ID, dto.getId());
        assertEquals(CHARACTER, dto.getCharacter());
        assertEquals(CLASS_LEVEL.toString(), dto.getLevel());
    }
}