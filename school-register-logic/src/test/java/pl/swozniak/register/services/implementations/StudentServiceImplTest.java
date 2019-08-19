package pl.swozniak.register.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.dtos.SchoolClassDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.mappers.StudentMapper;
import pl.swozniak.register.model.SchoolClass;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.repositories.StudentRepository;
import pl.swozniak.register.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
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
    void saveDTO(){
        when(studentRepository.save(any())).thenReturn(returnedStudent);
        when(mapper.studentToStudentDTO(any())).thenReturn(returnedDTO);
        when(mapper.studentDTOToStudent(any())).thenReturn(returnedStudent);

        StudentDTO returned = service.saveDTO(returnedDTO);

        assertNotNull(returned);

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
    void findAllByClassId(){
        Student student = Student.builder().id(ID + 1).build();
        List<Student> students = Arrays.asList(student, returnedStudent);

        when(studentRepository.findAllBySchoolClassId(anyLong())).thenReturn(students);
        when(mapper.studentToStudentDTO(any())).thenReturn(returnedDTO);

        List<StudentDTO> returned = service.findAllByClassId(ID);

        assertNotNull(returned);
        assertEquals(2, returned.size());
    }

    @Test
    void findAllByParentId(){
        Student student = Student.builder().id(ID + 1).build();
        List<Student> students = Arrays.asList(student, returnedStudent);

        when(studentRepository.findAllByParentId(anyLong())).thenReturn(students);
        when(mapper.studentToStudentDTO(any())).thenReturn(returnedDTO);

        List<StudentDTO> returned = service.findAllByParentId(ID);

        assertNotNull(returned);
        assertEquals(2, returned.size());
    }



    @Test
    void patchNotFoundById(){
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                service.patch(ID, returnedDTO));
    }

    @Test
    void patchDifferentSchoolClassId(){
        SchoolClass schoolClass = SchoolClass.builder().id(ID).build();
        returnedStudent.setSchoolClass(schoolClass);

        SchoolClass schoolClass1 = SchoolClass.builder().id(ID + 1).build();
        Student studentForClass = Student.builder().schoolClass(schoolClass1).build();

        SchoolClassDTO schoolClassDTO = new SchoolClassDTO();
        schoolClassDTO.setId(ID + 1);

        StudentDTO testing = new StudentDTO();
        testing.setSchoolClass(schoolClassDTO);

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(returnedStudent));
        when(studentRepository.save(any())).thenReturn(returnedStudent);
        when(mapper.studentToStudentDTO(any())).thenReturn(returnedDTO);
        when(mapper.studentDTOToStudent(any())).thenReturn(studentForClass);

        StudentDTO dto = service.patch(ID, testing);

        assertNotNull(dto);
        assertEquals(Long.valueOf(ID +1), returnedStudent.getSchoolClass().getId());

        verify(studentRepository).save(any());
    }

    @Test
    void patchSameSchoolClassId(){
        SchoolClass schoolClass = SchoolClass.builder().id(ID).build();
        returnedStudent.setSchoolClass(schoolClass);

        SchoolClassDTO schoolClassDTO = new SchoolClassDTO();
        schoolClassDTO.setId(ID);
        returnedDTO.setSchoolClass(schoolClassDTO);

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(returnedStudent));
        when(mapper.studentToStudentDTO(any())).thenReturn(returnedDTO);

        StudentDTO dto = service.patch(ID, returnedDTO);

        assertNotNull(dto);
        verify(studentRepository, times(0)).save(any());
    }

    @Test
    void getParentIdByStudentIdFound(){
        ParentDTO parentDTO = new ParentDTO();
        parentDTO.setId(ID);

        returnedDTO.setParent(parentDTO);

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(returnedStudent));
        when(mapper.studentToStudentDTO(any())).thenReturn(returnedDTO);

        Long returnedLong = service.getParentIdByStudentId(ID);

        assertEquals(Long.valueOf(ID), returnedLong);
    }

    @Test
    void getParentIdByStudentIdNotFound(){
        returnedDTO.setParent(null);

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(returnedStudent));
        when(mapper.studentToStudentDTO(any())).thenReturn(returnedDTO);

        assertThrows(ResourceNotFoundException.class,
                () -> service.getParentIdByStudentId(ID));
    }
}