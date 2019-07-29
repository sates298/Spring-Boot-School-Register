package pl.swozniak.register.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.swozniak.register.model.Lesson;
import pl.swozniak.register.services.LessonService;


@Controller
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping({"","/", "/all"})
    public String showAllLessons(Model model){
        model.addAttribute("lessons", lessonService.findAll());
        return "lesson/allLessons";
    }

    @GetMapping("/{id}")
    public String showOneLesson(@PathVariable Long id, Model model){
        Lesson found = lessonService.findById(id);
        if(found == null){
            return "notFound";
        }

        model.addAttribute("lesson", found);

        return "lesson/lessonDetails";
    }
}
