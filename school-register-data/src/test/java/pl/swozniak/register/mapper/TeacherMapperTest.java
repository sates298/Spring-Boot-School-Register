package pl.swozniak.register.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.swozniak.register.dtos.TeacherDTO;
import pl.swozniak.register.model.Teacher;

import static org.junit.jupiter.api.Assertions.*;

class TeacherMapperTest {

    public static final Long ID = 1L;
    public static final String FIRST_NAME = "first";
    public static final String LAST_NAME = "last";
    public static final String TELEPHONE = "123456789";
    TeacherMapper teacherMapper;

    @BeforeEach
    void setUp() {
        teacherMapper = Mappers.getMapper(TeacherMapper.class);
    }

    @Test
    void teacherToTeacherDTO() {
        Teacher teacher = Teacher.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .telephone(TELEPHONE)
                .build();

        TeacherDTO dto = teacherMapper.teacherToTeacherDTO(teacher);

        assertNotNull(dto);

        assertEquals(ID, dto.getId());
        assertEquals(FIRST_NAME, dto.getFirstName());
        assertEquals(LAST_NAME, dto.getLastName());
        assertEquals(TELEPHONE, dto.getTelephone());
    }
}