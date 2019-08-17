package pl.swozniak.register.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.swozniak.register.dtos.SchoolClassDTO;
import pl.swozniak.register.services.interfaces.SchoolClassService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @GetMapping("/{classId}")
    public ResponseEntity<SchoolClassDTO> getOneClass(@PathVariable Long classId){
        return new ResponseEntity<>(schoolClassService.findById(classId), HttpStatus.OK);
    }

    @GetMapping("/{classId}/students")
    public void getStudentsOneClass(@PathVariable Long classId, HttpServletResponse response) throws IOException {
        String redirect = "student/all/class-" + classId;
        response.sendRedirect(redirect);
    }
}