package pl.swozniak.register.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.Parent;
import pl.swozniak.register.model.SchoolClass;
import pl.swozniak.register.model.Student;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest(classes = {ParentMapperImpl.class, SchoolClassMapperImpl.class,
//        EnumMapper.class, StudentMapperImpl.class})
class StudentMapperTest {

    public static final Long ID = 1L;
    public static final String FIRST_NAME = "first";
    public static final String LAST_NAME = "last";

    @Mock
    EnumMapper enumMapper;

    @Mock
    SchoolClassMapper schoolClassMapper; // = new SchoolClassMapperImpl();

    @Mock
    ParentMapper parentMapper;

    @InjectMocks
//    @Autowired
    StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);

    @Test
    void studentToStudentDTO() {
        when(enumMapper.mapEnum(any())).thenCallRealMethod();


        Student student = Student.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .schoolClass(SchoolClass.builder()
                        .id(ID).build())
                .parent(Parent.builder()
                        .id(ID).build())
                .build();

        StudentDTO dto = studentMapper.studentToStudentDTO(student);

        assertNotNull(dto);

        assertEquals(ID, dto.getId());
        assertEquals(FIRST_NAME, dto.getFirstName());
        assertEquals(LAST_NAME, dto.getLastName());
        assertEquals(ID, dto.getSchoolClass().getId());
        assertEquals(ID, dto.getParent().getId());
    }
}