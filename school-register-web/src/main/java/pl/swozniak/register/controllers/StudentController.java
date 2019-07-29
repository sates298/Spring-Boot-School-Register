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
    private final GradeService gradeService;

    public StudentController(StudentService studentService, GradeService gradeService) {
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @GetMapping({"", "/", "/all"})
    public String showAllStudents(Model model){
        model.addAttribute("students", studentService.findAll());
        return "student/allStudents";
    }

    @GetMapping("/{id}")
    public String showOneStudent(Model model, @PathVariable Long id){
        Student found = studentService.findById(id);
        if(found == null){
            return "notFound";
        }

        model.addAttribute("student", found);
//        i think it is not necessary
        model.addAttribute("grades", gradeService.findAllByStudentId(id));

        return "student/studentDetails";
    }
}
