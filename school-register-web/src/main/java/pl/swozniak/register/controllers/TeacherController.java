package pl.swozniak.register.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.swozniak.register.dtos.TeacherDTO;
import pl.swozniak.register.services.interfaces.TeacherService;

import java.util.List;

@RestController
@RequestMapping("teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping({"", "/", "/all"})
    public ResponseEntity<List<TeacherDTO>> getAllTeachers(){
        return new ResponseEntity<>(teacherService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/all/subject-{subjectId}")
    public ResponseEntity<List<TeacherDTO>> getAllTeachersBySubjectId(@PathVariable Long subjectId){
        return new ResponseEntity<>(teacherService.findBySubjectId(subjectId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getOneTeacher(@PathVariable Long id){
        return new ResponseEntity<>(teacherService.findById(id), HttpStatus.OK);
    }
}
