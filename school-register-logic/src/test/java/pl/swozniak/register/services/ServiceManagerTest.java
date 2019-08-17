package pl.swozniak.register.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.services.interfaces.StudentService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceManagerTest {

    public static final Long ID = 1L;
    @Mock
    StudentService studentService;

    @InjectMocks
    ServiceManager manager;

    @Test
    void findStudentById() {
        StudentDTO returnedDTO = new StudentDTO();
        returnedDTO.setId(ID);

        when(studentService.findById(anyLong())).thenReturn(returnedDTO);

        StudentDTO returned = manager.findStudentById(ID);

        assertNotNull(returned);
        assertEquals(ID, returned.getId());

    }
}