package pl.swozniak.register.model.builders;

import pl.swozniak.register.model.Grade;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.model.Subject;
import pl.swozniak.register.model.enums.GradeValue;

public class GradeBuilder {
    private Long id;
    private GradeValue grade;
    private Subject subject;
    private String notes;
    private Student student;

    public GradeBuilder id(Long id){
        this.id = id;
        return this;
    }

    public GradeBuilder grade(GradeValue grade){
        this.grade = grade;
        return this;
    }

    public GradeBuilder subject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public GradeBuilder notes(String notes) {
        this.notes = notes;
        return this;
    }

    public GradeBuilder student(Student student) {
        this.student = student;
        return this;
    }

    public Grade build(){
        return new Grade(id, grade, subject, notes, student);
    }
}
