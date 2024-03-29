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
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.services.interfaces.ParentService;

import javax.servlet.http.HttpServletResponse;
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
class ParentControllerTest {

    public static final long ID = 1L;
    public static final String FIRST_NAME = "name";
    @Mock
    ParentService parentService;

    @InjectMocks
    ParentController parentController;

    @Mock
    HttpServletResponse response;

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

    private void testFindAllWithDifferentUri(String url) throws Exception {
        mockMvc.perform(get(url)
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
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        parentController.getChildren(ID, response);
        verify(response).sendRedirect(captor.capture());
        assertEquals("/student/all/parent-" + ID, captor.getValue());
    }
}
