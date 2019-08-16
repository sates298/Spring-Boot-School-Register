package pl.swozniak.register.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.Parent;
import pl.swozniak.register.services.ParentService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ParentControllerTest {

    public static final long ID = 1L;
    public static final String FIRST_NAME = "name";
    @Mock
    ParentService parentService;

    @InjectMocks
    ParentController parentController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(parentController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllParents() throws Exception {
        ParentDTO parentDTO1 = new ParentDTO();
        parentDTO1.setId(ID);

        ParentDTO parentDTO2 = new ParentDTO();
        parentDTO2.setId(ID + 1);

        List<ParentDTO> parents = Arrays.asList(parentDTO1, parentDTO2);

        when(parentService.findAll()).thenReturn(parents);

        testFindAllWithDifferentUri("/parent");
        testFindAllWithDifferentUri("/parent/");
        testFindAllWithDifferentUri("/parent/all");

    }

    private void testFindAllWithDifferentUri(String uri) throws Exception {
        mockMvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getOneParent() throws Exception {
        ParentDTO parentDTO = new ParentDTO();
        parentDTO.setId(ID);
        parentDTO.setFirstName(FIRST_NAME);

        when(parentService.findById(anyLong())).thenReturn(parentDTO);

        mockMvc.perform(get("/parent/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));

    }

    @Test
    void getChildren() throws Exception {
        StudentDTO studentDTO1 = new StudentDTO();
        studentDTO1.setId(ID);

        StudentDTO studentDTO2 = new StudentDTO();
        studentDTO2.setId(ID + 1);

        List<StudentDTO> studentDTOS = Arrays.asList(studentDTO1, studentDTO2);

        when(parentService.findChildrenByParentId(anyLong())).thenReturn(studentDTOS);

        mockMvc.perform(get("/parent/1/children")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}