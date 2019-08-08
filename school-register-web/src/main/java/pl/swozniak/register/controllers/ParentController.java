package pl.swozniak.register.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.services.ParentService;

import java.util.List;

@RestController
@RequestMapping("parent")
public class ParentController {

    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping({"", "/", "/all"})
    public ResponseEntity<List<ParentDTO>> getAllParents(){
        return new ResponseEntity<>(parentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentDTO> getOneParent(@PathVariable Long id){
        return new ResponseEntity<>(parentService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/children")
    public  ResponseEntity<List<StudentDTO>> getChildren(@PathVariable Long id){
        return new ResponseEntity<>(parentService.findById(id).getChildren(), HttpStatus.OK);
    }
}
