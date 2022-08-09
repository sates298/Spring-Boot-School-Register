package pl.swozniak.register.model.builders;

import pl.swozniak.register.model.Subject;
import pl.swozniak.register.model.Teacher;
import pl.swozniak.register.model.enums.SubjectName;

import java.util.List;

public class SubjectBuilder {
    private Long id;
    private SubjectName name;
    private List<Teacher> teachers;

    public SubjectBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public SubjectBuilder name(SubjectName name) {
        this.name = name;
        return this;
    }

    public SubjectBuilder teachers(List<Teacher> teachers) {
        this.teachers = teachers;
        return this;
    }

    public Subject build(){
        return new Subject(id, name, teachers);
    }
}
