package pl.swozniak.register.model.builders;

import pl.swozniak.register.model.Teacher;

public class TeacherBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private String telephone;

    public TeacherBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public TeacherBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public TeacherBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public TeacherBuilder telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public Teacher build(){
        return new Teacher(id, firstName, lastName, telephone);
    }
}
