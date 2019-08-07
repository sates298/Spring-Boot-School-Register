package pl.swozniak.register.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.dtos.SchoolClassDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.model.*;
import pl.swozniak.register.model.enums.ClassLevel;
import pl.swozniak.register.model.enums.GradeValue;
import pl.swozniak.register.services.GradeService;
import pl.swozniak.register.services.ParentService;
import pl.swozniak.register.services.SchoolClassService;
import pl.swozniak.register.services.StudentService;

import java.util.ArrayList;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final StudentService studentService;
    private final SchoolClassService schoolClassService;
    private final GradeService gradeService;
    private final ParentService parentService;

    public DataLoader(StudentService studentService, SchoolClassService schoolClassService, GradeService gradeService, ParentService parentService) {
        this.studentService = studentService;
        this.schoolClassService = schoolClassService;
        this.gradeService = gradeService;
        this.parentService = parentService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(schoolClassService.findAll().size() == 0){
            log.info("----start of loading sample data----");
//            loadData();
            log.info("----end of loading sample data----");
        }
    }

    private void loadData(){
        Parent parent = createParent(1L, "firstN", "lastN", "mail@mail.com", "123456789");

        ParentDTO parentDto = saveParent(parent);

        Student student1 = createStudent(1L, "first1", "last1");
        Student student2 = createStudent(2L, "first2", "last2");

        SchoolClass classA = createSchoolClass('A', 1L, ClassLevel.FIRST);
        SchoolClass classB = createSchoolClass('B', 2L, ClassLevel.THIRD);


        Grade grade3 = crateGrade(1L, GradeValue.THREE, 1, "exam3");
        Grade grade4 = crateGrade(2L, GradeValue.FOUR, 2, "exam4");


        SchoolClassDTO classAdto = saveSchoolClass(classA);
        SchoolClassDTO classBdto = saveSchoolClass(classB);
        log.info("save classes");

        student1.setSchoolClass(classA);
        student2.setSchoolClass(classA);

        student1.setParent(parent);
        student2.setParent(parent);

        StudentDTO studentdto = saveStudent(student1);
        StudentDTO student2dto = saveStudent(student2);

        log.info("save students");

        grade3.setStudent(student1);
        grade4.setStudent(student2);

        saveGrade(grade3);
        saveGrade(grade4);
        log.info("save grades");

    }

    private SchoolClass createSchoolClass(char a, long id, ClassLevel level) {
        return SchoolClass.builder()
                .character(a)
                .id(id)
                .level(level)
                .students(new ArrayList<>())
                .build();
    }

    private Parent createParent(long id, String first, String last, String email, String telephone) {
        return Parent.builder()
                .id(id)
                .firstName(first)
                .lastName(last)
                .email(email)
                .telephone(telephone)
                .build();
    }

    private Student createStudent(long id, String first, String last) {
        return Student.builder()
                .id(id)
                .firstName(first)
                .lastName(last)
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

    private StudentDTO saveStudent(Student student){
        return studentService.save(student);
    }

    private SchoolClassDTO saveSchoolClass(SchoolClass schoolClass){
        return schoolClassService.save(schoolClass);
    }

    private GradeDTO saveGrade(Grade grade){
        return gradeService.save(grade);
    }

    private ParentDTO saveParent(Parent parent){
        return parentService.save(parent);
    }

}
