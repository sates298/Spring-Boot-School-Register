package pl.swozniak.register.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.services.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> showOneStudent(@PathVariable Long id) {
        StudentDTO found = studentService.findById(id);

        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @GetMapping("/{id}/parent")
    public void getStudentParent(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Long parentId = studentService.findById(id).getParent().getId();
        String redirect = "/parent/" + parentId;

        response.sendRedirect(redirect);
    }

}
