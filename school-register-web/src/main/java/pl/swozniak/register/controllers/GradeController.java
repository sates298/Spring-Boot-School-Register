package pl.swozniak.register.controllers;

import org.springframework.web.bind.annotation.RestController;
import pl.swozniak.register.services.GradeService;

@RestController
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    
}
