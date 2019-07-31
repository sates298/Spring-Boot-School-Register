package pl.swozniak.register.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.swozniak.register.model.*;
import pl.swozniak.register.model.enums.GradeValue;
import pl.swozniak.register.services.GradeService;
import pl.swozniak.register.services.SchoolClassService;
import pl.swozniak.register.services.StudentService;

import java.util.ArrayList;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final StudentService studentService;
    private final SchoolClassService schoolClassService;
    private final GradeService gradeService;

    public DataLoader(StudentService studentService, SchoolClassService schoolClassService, GradeService gradeService) {
        this.studentService = studentService;
        this.schoolClassService = schoolClassService;
        this.gradeService = gradeService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(studentService.findAll().size() == 0){
            log.info("----start of loading sample data----");
            loadData();
            log.info("----end of loading sample data----");
        }
    }

    private void loadData(){
        Student student1 = createStudent(1L, "first1", "last1", 5);
        Student student2 = createStudent(2L, "first2", "last2", 10);

        SchoolClass classA = createSchoolClass('A', 1L, 1);
        SchoolClass classB = createSchoolClass('B', 2L, 3);


        Grade grade3 = crateGrade(1L, GradeValue.THREE, 1, "exam3");
        Grade grade4 = crateGrade(2L, GradeValue.FOUR, 2, "exam4");


        classA = saveSchoolClass(classA);
        classB = saveSchoolClass(classB);
        log.info("save classes");

        student1.setSchoolClass(classA);
        student2.setSchoolClass(classA);

        student1 = saveStudent(student1);
        student2 = saveStudent(student2);
        log.info("save students");

        grade3.setStudent(student1);
        grade4.setStudent(student2);

        saveGrade(grade3);
        saveGrade(grade4);
        log.info("save grades");

    }

    private SchoolClass createSchoolClass(char a, long id, int level) {
        return SchoolClass.builder()
                .character(a)
                .id(id)
                .level(level)
                .students(new ArrayList<>())
                .build();
    }

    private Student createStudent(long id, String first, String last, int number) {
        return Student.builder()
                .id(id)
                .firstName(first)
                .lastName(last)
                .number(number)
                .grades(new ArrayList<>())
                .build();
    }

    private Grade crateGrade(long id, GradeValue grade, int wage, String notes) {
        return Grade.builder()
                .id(id)
                .grade(grade)
//                .wage(wage)
                .notes(notes)
                .build();
    }

    private Student saveStudent(Student student){
        return studentService.save(student);
    }

    private SchoolClass saveSchoolClass(SchoolClass schoolClass){
        return schoolClassService.save(schoolClass);
    }

    private Grade saveGrade(Grade grade){
        return gradeService.save(grade);
    }

}
