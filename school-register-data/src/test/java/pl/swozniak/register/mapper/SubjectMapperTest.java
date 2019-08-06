package pl.swozniak.register.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.dtos.SubjectDTO;
import pl.swozniak.register.model.Subject;
import pl.swozniak.register.model.enums.SubjectName;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubjectMapperTest {

    public static final Long ID = 1L;
    public static final SubjectName SUBJECT_NAME = SubjectName.BIOLOGY;


    @Mock
    EnumMapper enumMapper;

    @InjectMocks
    SubjectMapper subjectMapper = Mappers.getMapper(SubjectMapper.class);


    @Test
    void subjectToSubjectDTO() {
        when(enumMapper.mapEnum(any())).thenCallRealMethod();

        Subject subject = Subject.builder()
                .id(ID)
                .name(SUBJECT_NAME)
                .build();

        SubjectDTO dto = subjectMapper.subjectToSubjectDTO(subject);

        assertNotNull(dto);
        assertEquals(ID, dto.getId());
        assertEquals(SUBJECT_NAME.toString(), dto.getName());
    }
}