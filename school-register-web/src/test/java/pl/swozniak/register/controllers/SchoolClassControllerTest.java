package pl.swozniak.register.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.swozniak.register.dtos.SchoolClassDTO;
import pl.swozniak.register.services.interfaces.SchoolClassService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SchoolClassControllerTest {

    public static final long ID = 1L;
    @Mock
    SchoolClassService schoolClassService;

    @InjectMocks
    SchoolClassController schoolClassController;

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
    void getOneClass() {
        //todo
    }
}