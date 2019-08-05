package pl.swozniak.register.model.builders;

import pl.swozniak.register.model.Subject;
import pl.swozniak.register.model.enums.SubjectName;

public class SubjectBuilder {
    private Long id;
    private SubjectName name;

    public SubjectBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public SubjectBuilder name(SubjectName name) {
        this.name = name;
        return this;
    }

    public Subject build(){
        return new Subject(id, name);
    }
}
