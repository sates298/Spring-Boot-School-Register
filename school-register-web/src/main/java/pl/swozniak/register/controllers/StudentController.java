package pl.swozniak.register.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.services.interfaces.StudentService;
import pl.swozniak.register.services.exceptions.ResourceNotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping({"", "/","/all"})
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping({"/all/class-{classId}"})
    public ResponseEntity<List<StudentDTO>> getAllStudentsByClassId(@PathVariable Long classId){
        return new ResponseEntity<>(studentService.findAllByClassId(classId),HttpStatus.OK);
    }

    @GetMapping("/all/parent-{parentId}")
    public ResponseEntity<List<StudentDTO>> getAllStudentsByParentId(@PathVariable Long parentId){
        return new ResponseEntity<>(studentService.findStudentsByParentId(parentId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getOneStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{studentId}/parent")
    public void getStudentParent(@PathVariable Long studentId, HttpServletResponse response) throws IOException {
        //todo how should it look like?
        ParentDTO parent = studentService.findById(studentId).getParent();
        if(parent == null) throw new ResourceNotFoundException();

        String redirect = "/parent/" + parent.getId();
        response.sendRedirect(redirect);
    }

    @PostMapping({"/new", "/add"})
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO student){
        return new ResponseEntity<>(studentService.saveDTO(student), HttpStatus.CREATED);
    }

    @PatchMapping({"/{id}", "/{id}/update"})
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO student){
        return new ResponseEntity<>(studentService.patch(id, student), HttpStatus.OK);
    }

}
