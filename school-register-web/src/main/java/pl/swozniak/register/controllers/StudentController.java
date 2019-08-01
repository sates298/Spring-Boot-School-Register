package pl.swozniak.register.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.services.GradeService;
import pl.swozniak.register.services.StudentService;

@Controller
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public String showOneStudent(Model model, @PathVariable Long id){
        Student found = studentService.findById(id);
        if(found == null){
            return "notFound";
        }

        model.addAttribute("student", found);

        return "student/studentDetails";
    }
}
