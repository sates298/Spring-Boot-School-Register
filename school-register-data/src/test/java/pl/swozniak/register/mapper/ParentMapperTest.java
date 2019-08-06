package pl.swozniak.register.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.model.Parent;

import static org.junit.jupiter.api.Assertions.*;

class ParentMapperTest {

    public static final String EMAIL = "12345@email.com";
    public static final String TELEPHONE = "123456789";
    public static final String LAST_NAME = "last";
    public static final String FIRST = "first";
    public static final Long ID = 1L;


    ParentMapper parentMapper ;


    @BeforeEach
    void setUp() {
        parentMapper = Mappers.getMapper(ParentMapper.class);
    }

    @Test
    void parentToParentDTO() {
        Parent parent = Parent.builder()
                .id(ID)
                .firstName(FIRST)
                .lastName(LAST_NAME)
                .telephone(TELEPHONE)
                .email(EMAIL)
                .build();

        ParentDTO dto = parentMapper.parentToParentDTO(parent);

        assertNotNull(dto);

        assertEquals(ID, dto.getId());
        assertEquals(FIRST, dto.getFirstName());
        assertEquals(LAST_NAME, dto.getLastName());
        assertEquals(TELEPHONE, dto.getTelephone());
        assertEquals(EMAIL, dto.getEmail());
    }
}