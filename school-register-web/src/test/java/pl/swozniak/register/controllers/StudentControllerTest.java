package pl.swozniak.register.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.services.interfaces.StudentService;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    public static final String FIRST_NAME = "name";
    public static final long ID = 1L;
    @Mock
    StudentService studentService;

    @InjectMocks
    StudentController studentController;

    @Mock
    HttpServletResponse response;

    MockMvc mockMvc;

    private StudentDTO studentDTO;

    @BeforeEach
    void setUp() {
        studentDTO = new StudentDTO();
        studentDTO.setId(ID);
        studentDTO.setFirstName(FIRST_NAME);

        mockMvc = MockMvcBuilders
                .standaloneSetup(studentController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllStudents() throws Exception {
        List<StudentDTO> studentDTOS = List.of(new StudentDTO(), studentDTO);

        when(studentService.findAll()).thenReturn(studentDTOS);

        testFindAllWithDifferentUri("/student");
        testFindAllWithDifferentUri("/student/");
        testFindAllWithDifferentUri("/student/all");
    }


    @Test
    void getAllStudentsByClassId() throws Exception {
        List<StudentDTO> studentDTOS = List.of(new StudentDTO(), studentDTO);

        when(studentService.findAllByClassId(anyLong())).thenReturn(studentDTOS);

        testFindAllWithDifferentUri("/student/all/class-1");
    }

    @Test
    void getAllStudentsByParentId() throws Exception {
        List<StudentDTO> studentDTOS = List.of(new StudentDTO(), studentDTO);

        when(studentService.findAllByParentId(anyLong())).thenReturn(studentDTOS);

        testFindAllWithDifferentUri("/student/all/parent-1");
    }

    private void testFindAllWithDifferentUri(String url) throws Exception {
        mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getOneStudent() throws Exception {
        when(studentService.findById(anyLong())).thenReturn(studentDTO);

        mockMvc.perform(get("/student/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
    }

    @Test
    void getStudentParent() throws IOException {
        when(studentService.getParentIdByStudentId(anyLong())).thenReturn(ID);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        studentController.getStudentParent(ID, response);
        verify(response).sendRedirect(captor.capture());
        assertEquals("/parent/" + ID, captor.getValue());
    }

    @Test
    void addStudent() throws Exception {
        when(studentService.saveDTO(any())).thenReturn(studentDTO);

        testPostWithDifferentUrl("/student/new");
        testPostWithDifferentUrl("/student/add");
    }

    private void testPostWithDifferentUrl(String url) throws Exception{
        mockMvc.perform(post(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(studentDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
    }

    @Test
    void updateStudent() throws Exception {
        when(studentService.patch(anyLong(), any())).thenReturn(studentDTO);

        testPatchWithDifferentUrl("/student/1");
        testPatchWithDifferentUrl("/student/1/update");
    }

    private void testPatchWithDifferentUrl(String url) throws Exception{
        mockMvc.perform(patch(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(studentDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
    }
}