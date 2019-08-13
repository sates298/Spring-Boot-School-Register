package pl.swozniak.register.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.services.StudentService;
import pl.swozniak.register.services.exceptions.ResourceNotFoundException;

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
        ParentDTO parent = studentService.findById(id).getParent();
        if(parent == null) throw new ResourceNotFoundException();

        String redirect = "/parent/" + parent.getId();

        response.sendRedirect(redirect);
    }

    @PostMapping({"/new", "/add"})
    public ResponseEntity<StudentDTO> addStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }

    @PatchMapping({"/{id}", "/{id}/update"})
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody Student student){
        return new ResponseEntity<>(studentService.patch(id, student), HttpStatus.OK);
    }

}
