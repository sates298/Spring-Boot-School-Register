package pl.swozniak.register.model.builders;

import pl.swozniak.register.model.Grade;
import pl.swozniak.register.model.Parent;
import pl.swozniak.register.model.SchoolClass;
import pl.swozniak.register.model.Student;

import java.util.List;

public class StudentBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private SchoolClass schoolClass;
    private List<Grade> grades;
    private Parent parent;


    public StudentBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public StudentBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StudentBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StudentBuilder schoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
        return this;
    }

    public StudentBuilder grades(List<Grade> grades) {
        this.grades = grades;
        return this;
    }

    public StudentBuilder parent(Parent parent) {
        this.parent = parent;
        return this;
    }

    public Student build(){
        return new Student(id,firstName,lastName,schoolClass,grades,parent);
    }
}
