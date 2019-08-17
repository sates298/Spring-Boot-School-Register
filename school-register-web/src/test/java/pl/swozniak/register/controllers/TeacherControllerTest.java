package pl.swozniak.register.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.swozniak.register.dtos.TeacherDTO;
import pl.swozniak.register.services.TeacherService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({MockitoExtension.class})
class TeacherControllerTest {

    public static final long ID = 1L;
    public static final String FIRST_NAME = "first";

    @Mock
    TeacherService teacherService;

    @InjectMocks
    TeacherController teacherController;

    MockMvc mockMvc;

    TeacherDTO returnedTeacher;

    @BeforeEach
    void setUp() {
        returnedTeacher = new TeacherDTO();
        returnedTeacher.setId(ID);
        returnedTeacher.setFirstName(FIRST_NAME);

        mockMvc = MockMvcBuilders
                .standaloneSetup(teacherController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllTeachers() throws Exception {
        List<TeacherDTO> teachers = new ArrayList<>();
        teachers.add(returnedTeacher);
        teachers.add(new TeacherDTO());

        when(teacherService.findAll()).thenReturn(teachers);

        testFindAllWithDifferentUri("/teacher");
        testFindAllWithDifferentUri("/teacher/");
        testFindAllWithDifferentUri("/teacher/all");
    }

    private void testFindAllWithDifferentUri(String uri) throws Exception {
        mockMvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getOneTeacher() throws Exception {
        when(teacherService.findById(anyLong())).thenReturn(returnedTeacher);

        mockMvc.perform(get("/teacher/"+ID)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
    }
}