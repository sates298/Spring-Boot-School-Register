package pl.swozniak.register.backgroundjob;

import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.enums.GradeValue;
import pl.swozniak.register.services.interfaces.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BackgroundJobStudentFinder {

    public static final GradeValue GRADE_VALUE = GradeValue.ONE;
    public static final Integer NUMBER_OF_CORRECT_GRADES = 3;

    private final StudentService studentService;

    public BackgroundJobStudentFinder(StudentService studentService) {
        this.studentService = studentService;
    }

    public List<StudentDTO> findAllStudentsWithOnes(){
        List<StudentDTO> all = studentService.findAll();
        return filterStudents(all);
    }

    private List<StudentDTO> filterStudents(List<StudentDTO> students){
        return students
                .stream()
                .filter(student ->
                        filterGrades(student.getGrades()).size() >= NUMBER_OF_CORRECT_GRADES)
                .collect(Collectors.toList());
    }

    private List<GradeDTO> filterGrades(List<GradeDTO> grades){
        return grades
                .stream()
                .filter(grade ->
                        GRADE_VALUE.toString()
                                .equals(grade.getGrade()))
                .collect(Collectors.toList());
    }
}
