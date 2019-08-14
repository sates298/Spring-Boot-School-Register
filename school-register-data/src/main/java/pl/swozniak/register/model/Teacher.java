package pl.swozniak.register.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.swozniak.register.model.builders.TeacherBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "teachers")
public class Teacher extends Person {

    @Column(name = "telephone_nr")
    @Size(min = 9, max = 15)
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

//    @Builder
    public Teacher(Long id, String firstName, String lastName, @Size(min = 9, max = 15) String telephone, Subject subject) {
        super(id, firstName, lastName);
        this.telephone = telephone;
        this.subject = subject;
    }

    public static TeacherBuilder builder(){
        return new TeacherBuilder();
    }
}
