package pl.swozniak.register.model.builders;

import pl.swozniak.register.model.SchoolClass;
import pl.swozniak.register.model.Student;
import pl.swozniak.register.model.enums.ClassLevel;

import java.util.List;

public class SchoolClassBuilder {
    private Long id;
    private List<Student> students;
    private Character character;
    private ClassLevel level;

    public SchoolClassBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public SchoolClassBuilder students(List<Student> students) {
        this.students = students;
        return this;
    }

    public SchoolClassBuilder character(Character character) {
        this.character = character;
        return this;
    }

    public SchoolClassBuilder level(ClassLevel level) {
        this.level = level;
        return this;
    }

    public SchoolClass build(){
        return new SchoolClass(id, students, character, level);
    }
}
