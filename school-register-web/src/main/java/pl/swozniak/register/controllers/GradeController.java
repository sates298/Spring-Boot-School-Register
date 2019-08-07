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
import java.util.stream.Collectors;

@RestController
@RequestMapping("student/{student_id}/grades")
public class GradeController {

    private final GradeService gradeService;


    public GradeController(GradeService gradeService, StudentService studentService) {
        this.gradeService = gradeService;
    }
    @GetMapping({"", "/", "/all"})
    public ResponseEntity<List<GradeDTO>> getAllGrades(@PathVariable Long student_id){
        List<GradeDTO> grades = gradeService.findAll()
                .stream()
                .filter(gradeDTO -> gradeDTO
                                .getStudent()
                                .getId()
                                .equals(student_id))
                .collect(Collectors.toList());

        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    @GetMapping("/{grade_id}")
    public ResponseEntity<GradeDTO> getGrade(@PathVariable Long grade_id){
        return new ResponseEntity<>(gradeService.findById(grade_id), HttpStatus.OK);
    }

}
