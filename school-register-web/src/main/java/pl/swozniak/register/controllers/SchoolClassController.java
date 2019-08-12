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

import java.util.List;


@RestController
@RequestMapping("class")
public class SchoolClassController {

    private final SchoolClassService schoolClassService;

    public SchoolClassController(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    @GetMapping({"/all", "", "/"})
    public ResponseEntity<List<SchoolClassDTO>> getAllClasses(){
        return new ResponseEntity<>(schoolClassService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{class_id}")
    public ResponseEntity<List<StudentDTO>> getOneClass(@PathVariable Long class_id){
        return new ResponseEntity<>(schoolClassService.findById(class_id).getStudents(), HttpStatus.OK);
    }
}