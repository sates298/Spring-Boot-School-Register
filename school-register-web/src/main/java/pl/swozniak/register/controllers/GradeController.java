package pl.swozniak.register.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.swozniak.register.dtos.GradeDTO;
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

    @GetMapping("/{gradeId}")
    public ResponseEntity<GradeDTO> getGrade(@PathVariable Long gradeId){
        return new ResponseEntity<>(gradeService.findById(gradeId), HttpStatus.OK);
    }

    @PostMapping({"/new", "/add"})
    public ResponseEntity<GradeDTO> addGrade(@RequestBody GradeDTO grade, @PathVariable Long student_id){
        return new ResponseEntity<>(gradeService.saveDTO(grade, student_id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{gradeId}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long gradeId){
        gradeService.deleteById(gradeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{gradeId}/resit")
    public ResponseEntity<GradeDTO> patchGrade(@PathVariable Long gradeId, @RequestBody GradeDTO grade){
        return new ResponseEntity<>(gradeService.patch(gradeId, grade), HttpStatus.OK);
    }


}
