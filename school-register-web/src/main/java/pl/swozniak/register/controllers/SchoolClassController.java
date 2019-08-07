package pl.swozniak.register.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.swozniak.register.dtos.SchoolClassDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.services.SchoolClassService;
import pl.swozniak.register.services.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("class")
public class SchoolClassController {

    private final SchoolClassService schoolClassService;
    private final StudentService studentService;

    public SchoolClassController(SchoolClassService schoolClassService, StudentService studentService) {
        this.schoolClassService = schoolClassService;
        this.studentService = studentService;
    }

    @GetMapping({"/all", "", "/"})
    public ResponseEntity<List<SchoolClassDTO>> getAllClasses(){
        return new ResponseEntity<>(schoolClassService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{class_id}")
    public ResponseEntity<List<StudentDTO>> getOneClass(@PathVariable Long class_id){
        return new ResponseEntity<>
                (studentService
                        .findAll()
                        .stream()
                        .filter(student -> student
                                .getSchoolClass()
                                .getId()
                                .equals(class_id))
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }
}