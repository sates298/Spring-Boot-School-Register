package pl.swozniak.register.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.services.interfaces.GradeService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GradeControllerTest {

    public static final Long ID = 1L;
    public static final String STRING = "grade";

    @Mock
    GradeService gradeService;

    @InjectMocks
    GradeController gradeController;

    MockMvc mockMvc;

    private GradeDTO gradeDTO;

    @BeforeEach
    void setUp() {
        gradeDTO = new GradeDTO();
        gradeDTO.setId(ID);
        gradeDTO.setGrade(STRING);
        gradeDTO.setStudent(new StudentDTO());

        mockMvc = MockMvcBuilders
                .standaloneSetup(gradeController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllGrades() throws Exception {
        GradeDTO gradeDTO2 = new GradeDTO();
        gradeDTO2.setId(ID + 1);
        gradeDTO2.setStudent(new StudentDTO());

        List<GradeDTO> gradeDTOS = Arrays.asList(gradeDTO, gradeDTO2);

        when(gradeService.findAllByStudentId(anyLong())).thenReturn(gradeDTOS);

        testFindAllWithDifferentUri("/student/1/grades");
        testFindAllWithDifferentUri("/student/1/grades/");
        testFindAllWithDifferentUri("/student/1/grades/all");

    }

    private void testFindAllWithDifferentUri(String uri) throws Exception {
        mockMvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getGrade() throws Exception {
        when(gradeService.findById(anyLong())).thenReturn(gradeDTO);

        mockMvc.perform(get("/student/1/grades/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.grade", equalTo(STRING)));

    }

    @Test
    void addGrade() throws Exception {
        when(gradeService.saveDTO(any(), anyLong())).thenReturn(gradeDTO);

        testAddNewGradeWithDifferentUri("/student/1/grades/new", gradeDTO);
        testAddNewGradeWithDifferentUri("/student/1/grades/add", gradeDTO);

    }

    private void testAddNewGradeWithDifferentUri(String uri, GradeDTO grade) throws Exception {
        mockMvc.perform(post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(grade)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.grade", equalTo(STRING)));
    }
    @Test
    void deleteGrade() throws Exception {
        mockMvc.perform(delete("/student/1/grades/1"))
                .andExpect(status().isOk());

        verify(gradeService).deleteById(anyLong());
    }

    @Test
    void patchGrade() throws Exception {
        when(gradeService.patch(anyLong(), any())).thenReturn(gradeDTO);

        mockMvc.perform(patch("/student/1/grades/1/resit")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(gradeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.grade", equalTo(STRING)));
    }
}