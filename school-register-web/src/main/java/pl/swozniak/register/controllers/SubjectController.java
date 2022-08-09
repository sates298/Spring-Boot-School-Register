package pl.swozniak.register.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.swozniak.register.dtos.SubjectDTO;
import pl.swozniak.register.model.Subject;
import pl.swozniak.register.services.interfaces.SubjectService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping({"", "/", "/all"})
    public ResponseEntity<List<SubjectDTO>> getAllSubjects(){
        return new ResponseEntity<>(subjectService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> getOneSubject(@PathVariable Long id){
        return new ResponseEntity<>(subjectService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/teachers")
    public void getAllTeachersBySubjectId(@PathVariable Long id, HttpServletResponse response) throws IOException {
        String redirect = "/teacher/all/subject-" + id;
        response.sendRedirect(redirect);
    }
}
