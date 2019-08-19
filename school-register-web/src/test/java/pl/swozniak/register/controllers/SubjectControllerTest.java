package pl.swozniak.register.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.swozniak.register.dtos.SubjectDTO;
import pl.swozniak.register.services.interfaces.SubjectService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
class SubjectControllerTest {

    public static final String STRING = "name";
    public static final long ID = 1L;
    @Mock
    SubjectService subjectService;

    @InjectMocks
    SubjectController subjectController;

    @Mock
    HttpServletResponse response;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(subjectController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllSubjects() throws Exception {
        List<SubjectDTO> subjectDTOS = List.of(new SubjectDTO(), new SubjectDTO());
        when(subjectService.findAll()).thenReturn(subjectDTOS);

        testFindAllWithDifferentUri("/subject");
        testFindAllWithDifferentUri("/subject/");
        testFindAllWithDifferentUri("/subject/all");

    }

    private void testFindAllWithDifferentUri(String url) throws Exception {
        mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getOneSubject() throws Exception {
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setName(STRING);
        subjectDTO.setTeachers(List.of());

        when(subjectService.findById(any())).thenReturn(subjectDTO);

        mockMvc.perform(get("/subject/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(STRING)));
    }

    @Test
    void getAllTeachersBySubjectId() throws IOException {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        subjectController.getAllTeachersBySubjectId(ID, response);
        verify(response).sendRedirect(captor.capture());
        assertEquals("/teacher/all/subject-"+ID, captor.getValue());
    }
}