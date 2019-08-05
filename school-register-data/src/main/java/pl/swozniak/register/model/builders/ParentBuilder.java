package pl.swozniak.register.model.builders;

import pl.swozniak.register.model.Parent;
import pl.swozniak.register.model.Student;

import java.util.List;

public class ParentBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private List<Student> children;

    public ParentBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ParentBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ParentBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ParentBuilder email(String email) {
        this.email = email;
        return this;
    }

    public ParentBuilder telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public ParentBuilder children(List<Student> children) {
        this.children = children;
        return this;
    }

    public Parent build(){
        return new Parent(id, firstName, lastName, email, telephone, children);
    }
}
