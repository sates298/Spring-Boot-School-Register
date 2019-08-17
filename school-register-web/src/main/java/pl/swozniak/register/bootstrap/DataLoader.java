package pl.swozniak.register.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.*;
import pl.swozniak.register.model.*;
import pl.swozniak.register.model.enums.ClassLevel;
import pl.swozniak.register.model.enums.GradeValue;
import pl.swozniak.register.model.enums.SubjectName;
import pl.swozniak.register.services.interfaces.*;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final StudentService studentService;
    private final SchoolClassService schoolClassService;
    private final GradeService gradeService;
    private final ParentService parentService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;

    public DataLoader(StudentService studentService, SchoolClassService schoolClassService, GradeService gradeService,
                      ParentService parentService, TeacherService teacherService, SubjectService subjectService) {
        this.studentService = studentService;
        this.schoolClassService = schoolClassService;
        this.gradeService = gradeService;
        this.parentService = parentService;
        this.teacherService = teacherService;
        this.subjectService = subjectService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(schoolClassService.findAll().size() == 0){
            log.info("----start of loading sample data----");
            loadData();
            log.info("----end of loading sample data----");
        }
    }

    private void loadData(){
        Parent parent1 = createParent(1L, "firstP1", "lastP1", "mail@mail.com", "123456789");
        Parent parent2 =
                createParent(2L, "firstP2", "lastP2", "1.testowe.2.moje.3@gmail.com", "234567890");

        ParentDTO parentDTO1 = saveParent(parent1);
        ParentDTO parentDTO2 = saveParent(parent2);
        log.info("saveDTO parents");

        SchoolClass classA = createSchoolClass('A', 1L, ClassLevel.FIRST);
        SchoolClass classB = createSchoolClass('B', 2L, ClassLevel.THIRD);

        SchoolClassDTO classDTOA = saveSchoolClass(classA);
        SchoolClassDTO classDTOB = saveSchoolClass(classB);
        log.info("saveDTO classes");

        Student student1 = createStudent(1L, "firstS1", "lastS1", parent1, classA);
        Student student2 = createStudent(2L, "firstS2", "lastS2", parent2, classA);
        Student student3 = createStudent(3L, "firstS3", "lastS3", parent2, classB);

        StudentDTO studentDTO1 = saveStudent(student1);
        StudentDTO studentDTO2 = saveStudent(student2);
        StudentDTO studentDTO3 = saveStudent(student3);
        log.info("saveDTO students");

        Subject subject1 = createSubject(1L, SubjectName.MATHS);
        Subject subject2 = createSubject(2L, SubjectName.BIOLOGY);
        Subject subject3 = createSubject(3L, SubjectName.IT);
        Subject subject4 = createSubject(4L, SubjectName.BEHAVIOR);

        SubjectDTO subjectDTO1 = saveSubject(subject1);
        SubjectDTO subjectDTO2 = saveSubject(subject2);
        SubjectDTO subjectDTO3 = saveSubject(subject3);
        SubjectDTO subjectDTO4 = saveSubject(subject4);
        log.info("saveDTO subjects");

        Grade grade1 = createGrade(1L, GradeValue.THREE, subject1, "exam3", student1);
        Grade grade2 = createGrade(2L, GradeValue.FOUR, subject2, "exam4", student1);
        Grade grade3 = createGrade(3L, GradeValue.ONE, subject4, "bad behavior note", student2);
        Grade grade4 = createGrade(4L, GradeValue.FIVEMINUS, subject3, "", student3);
        Grade grade5 = createGrade(5L, GradeValue.ONE, subject4, "   ", student3);

        GradeDTO gradeDTO1 = saveGrade(grade1);
        GradeDTO gradeDTO2 = saveGrade(grade2);
        GradeDTO gradeDTO3 = saveGrade(grade3);
        GradeDTO gradeDTO4 = saveGrade(grade4);
        GradeDTO gradeDTO5 = saveGrade(grade5);
        log.info("saveDTO grades");

        Teacher teacher1 = createTeacher(1L, "firstT1", "lastT1", "098765432", subject1);
        Teacher teacher2 = createTeacher(2L, "firstT2", "lastT2", "987654321", subject2);
        Teacher teacher3 = createTeacher(3L, "firstT3", "lastT3", "876543210", subject3);
        Teacher teacher4 = createTeacher(4L, "firstT4", "lastT4", "765432109", subject3);

        TeacherDTO teacherDTO1 = saveTeacher(teacher1);
        TeacherDTO teacherDTO2 = saveTeacher(teacher2);
        TeacherDTO teacherDTO3 = saveTeacher(teacher3);
        TeacherDTO teacherDTO4 = saveTeacher(teacher4);
        log.info("saveDTO teachers");
    }

    private Subject createSubject(long id, SubjectName name){
        return Subject.builder()
                .id(id)
                .name(name)
                .build();
    }

    private SchoolClass createSchoolClass(char a, long id, ClassLevel level) {
        return SchoolClass.builder()
                .character(a)
                .id(id)
                .level(level)
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

    private Student createStudent(long id, String first, String last, Parent parent, SchoolClass schoolClass) {
        return Student.builder()
                .id(id)
                .firstName(first)
                .lastName(last)
                .parent(parent)
                .schoolClass(schoolClass)
                .build();
    }

    private Teacher createTeacher(long id, String first, String last, String telephone, Subject subject){
        return Teacher.builder()
                .id(id)
                .firstName(first)
                .lastName(last)
                .telephone(telephone)
                .subject(subject)
                .build();
    }

    private Grade createGrade(long id, GradeValue grade, Subject subject, String notes, Student student) {
        return Grade.builder()
                .id(id)
                .grade(grade)
                .subject(subject)
                .notes(notes)
                .student(student)
                .build();
    }

    private SubjectDTO saveSubject(Subject subject){
        return subjectService.save(subject);
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

    private TeacherDTO saveTeacher(Teacher teacher){
        return teacherService.save(teacher);
    }

}
