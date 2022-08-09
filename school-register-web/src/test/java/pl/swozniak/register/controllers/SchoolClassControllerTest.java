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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.swozniak.register.dtos.SchoolClassDTO;
import pl.swozniak.register.services.interfaces.SchoolClassService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SchoolClassControllerTest {

    public static final long ID = 1L;
    public static final char CHARACTER = 'A';
    @Mock
    SchoolClassService schoolClassService;

    @InjectMocks
    SchoolClassController schoolClassController;

    @Mock
    HttpServletResponse response;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(schoolClassController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllClasses() throws Exception {
        SchoolClassDTO classDTO1 = new SchoolClassDTO();
        classDTO1.setId(ID);

        SchoolClassDTO classDTO2 = new SchoolClassDTO();
        classDTO2.setId(ID + 1);

        List<SchoolClassDTO> classes = Arrays.asList(classDTO1, classDTO2);

        when(schoolClassService.findAll()).thenReturn(classes);

        mockMvc.perform(get("/class/all")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getOneClass() throws Exception {
        SchoolClassDTO schoolClassDTO = new SchoolClassDTO();
        schoolClassDTO.setCharacter(CHARACTER);

        when(schoolClassService.findById(anyLong())).thenReturn(schoolClassDTO);

        mockMvc.perform(get("/class/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.character", equalTo(String.valueOf(CHARACTER))));
    }

    @Test
    void getStudentsOneClass() throws IOException {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        schoolClassController.getStudentsOneClass(ID, response);
        verify(response).sendRedirect(captor.capture());
        assertEquals("/student/all/class-"+ID, captor.getValue());
    }
}