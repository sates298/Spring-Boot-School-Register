package pl.swozniak.register.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "teachers")
public class Teacher extends Person {

    @Email
    @Column(name = "email")
    private String email;

    @Builder
    public Teacher(Long id, String firstName, String lastName, @Email String email) {
        super(id, firstName, lastName);
        this.email = email;
    }
}
