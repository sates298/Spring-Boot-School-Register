package pl.swozniak.register.mapper;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.model.Grade;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.model.Subject;
import pl.swozniak.register.model.enums.GradeValue;
import pl.swozniak.register.model.enums.SubjectName;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GradeMapperTest {
    public static final GradeValue GRADE_VALUE = GradeValue.FIVE;
    public static final SubjectName SUBJECT_NAME = SubjectName.IT;
    public static final String FIRST_NAME = "name";
    public static final String NOTES = "notes";

    @Mock
    EnumMapper enumMapper;

    @Mock
    StudentMapper studentMapper;

    @Mock
    SubjectMapper subjectMapper;

    @InjectMocks
    GradeMapper gradeMapper = Mappers.getMapper(GradeMapper.class);


    @Test
    void gradeToGradeDTO() {
        when(enumMapper.mapEnum(any())).thenCallRealMethod();
//        when(subjectMapper.subjectToSubjectDTO(any())).thenCallRealMethod();
//        when(studentMapper.studentToStudentDTO(any())).thenCallRealMethod();

        Grade tested = Grade.builder()
                .grade(GRADE_VALUE)
//                .subject(Subject.builder()
//                        .name(SUBJECT_NAME)
//                        .build())
//                .student(Student.builder()
//                        .firstName(FIRST_NAME).build())
                .notes(NOTES)
                .build();


        GradeDTO dto  = gradeMapper.gradeToGradeDTO(tested);

        assertEquals(NOTES, dto.getNotes());
        assertEquals(GRADE_VALUE.toString(), dto.getGrade());

        assertEquals(FIRST_NAME, dto.getStudent().getFirstName());
        assertEquals(SUBJECT_NAME.toString(), dto.getSubject().getName());
    }
}