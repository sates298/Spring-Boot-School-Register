package pl.swozniak.register.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.services.GradeService;
import pl.swozniak.register.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("student/{student_id}/grades")
public class GradeController {

    private final GradeService gradeService;
    private final StudentService studentService;

    public GradeController(GradeService gradeService, StudentService studentService) {
        this.gradeService = gradeService;
        this.studentService = studentService;
    }

    @GetMapping({"", "/", "/all"})
    public ResponseEntity<List<GradeDTO>> getAllGrades(@PathVariable Long student_id){
        List<GradeDTO> grades = studentService.findById(student_id).getGrades();

        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    @GetMapping("/{grade_id}")
    public ResponseEntity<GradeDTO> getGrade(@PathVariable Long grade_id){
        return new ResponseEntity<>(gradeService.findById(grade_id), HttpStatus.OK);
    }

}
