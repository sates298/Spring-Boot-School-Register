package pl.swozniak.register.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.mappers.StudentMapper;
import pl.swozniak.register.model.SchoolClass;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.repositories.StudentRepository;
import pl.swozniak.register.services.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    public static final long ID = 1L;

    @Mock
    StudentRepository studentRepository;

    @Mock
    StudentMapper mapper;

    @InjectMocks
    StudentServiceImpl service;

    private Student returnedStudent;
    private StudentDTO returnedDTO;

    @BeforeEach
    void setUp() {
        returnedStudent = Student.builder().id(ID).build();
        returnedDTO = new StudentDTO();
        returnedDTO.setId(ID);
    }

    @Test
    void findAll(){
        List<Student> returnedStudents = new ArrayList<>();
        returnedStudents.add(returnedStudent);
        returnedStudents.add(Student.builder().id(ID +1).build());

        when(studentRepository.findAll()).thenReturn(returnedStudents);
        when(mapper.studentToStudentDTO(any())).thenReturn(returnedDTO);

        List<StudentDTO> students = service.findAll();

        assertNotNull(students);
        assertEquals(2, students.size());
    }

    @Test
    void findById() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(returnedStudent));
        when(mapper.studentToStudentDTO(any())).thenReturn(returnedDTO);

        StudentDTO student = service.findById(ID);
        assertNotNull(student);
    }

    @Test
    void findByIdNotFound(){
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                service.findById(ID));
    }

    @Test
    void save() {
        Student studentToSave = Student.builder().id(ID).build();

        when(studentRepository.save(any())).thenReturn(returnedStudent);
        when(mapper.studentToStudentDTO(any())).thenReturn(returnedDTO);

        StudentDTO saved = service.save(studentToSave);

        assertNotNull(saved);
        verify(studentRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnedStudent);

        verify(studentRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(ID);

        verify(studentRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void patchNotFoundById(){
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                service.patch(ID, returnedStudent));
    }

    @Test
    void patchDifferentSchoolClassId(){
        SchoolClass schoolClass = SchoolClass.builder().id(ID).build();
        SchoolClass schoolClass1 = SchoolClass.builder().id(ID + 1).build();
        returnedStudent.setSchoolClass(schoolClass);
        Student testing = Student.builder().id(ID + 1).schoolClass(schoolClass1).build();

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(returnedStudent));
        when(studentRepository.save(any())).thenReturn(returnedStudent);
        when(mapper.studentToStudentDTO(any())).thenReturn(returnedDTO);

        StudentDTO dto = service.patch(ID, testing);

        assertNotNull(dto);
        assertEquals(Long.valueOf(ID +1), returnedStudent.getSchoolClass().getId());

        verify(studentRepository).save(any());
    }

    @Test
    void patchSameSchoolClassId(){
        SchoolClass schoolClass = SchoolClass.builder().id(ID).build();
        returnedStudent.setSchoolClass(schoolClass);

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(returnedStudent));
        when(mapper.studentToStudentDTO(any())).thenReturn(returnedDTO);

        StudentDTO dto = service.patch(ID, returnedStudent);

        assertNotNull(dto);
        verify(studentRepository, times(0)).save(any());
    }
}