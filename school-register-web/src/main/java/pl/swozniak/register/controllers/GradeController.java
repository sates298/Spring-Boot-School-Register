package pl.swozniak.register.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.model.Grade;
import pl.swozniak.register.services.GradeService;

import java.util.List;

@RestController
@RequestMapping("student/{student_id}/grades")
public class GradeController {

    private final GradeService gradeService;


    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping({"", "/", "/all"})
    public ResponseEntity<List<GradeDTO>> getAllGrades(@PathVariable Long student_id){
        return new ResponseEntity<>(gradeService.findAllByStudentId(student_id), HttpStatus.OK);
    }

    @GetMapping("/{grade_id}")
    public ResponseEntity<GradeDTO> getGrade(@PathVariable Long grade_id){
        return new ResponseEntity<>(gradeService.findById(grade_id), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<GradeDTO> addGrade(@RequestBody Grade grade, @PathVariable Long student_id){
        return new ResponseEntity<>(gradeService.saveGrade(grade, student_id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{grade_id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long grade_id){
        gradeService.deleteById(grade_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{grade_id}/resit")
    public ResponseEntity<GradeDTO> putGrade(@PathVariable Long grade_id, @RequestBody Grade grade){
        return new ResponseEntity<>(gradeService.put(grade_id, grade), HttpStatus.OK);
    }


}
