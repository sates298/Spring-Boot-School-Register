package pl.swozniak.register.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.swozniak.register.model.builders.ParentBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "parents")
public class Parent extends Person {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 9, max = 15)
    private String telephone;


    @OneToMany(mappedBy = "parent")
    private List<Student> children = new ArrayList<>();

//    @Builder
    public Parent(Long id, String firstName, String lastName, @Email @NotBlank String email,
                  @NotBlank @Size(min = 9, max = 15) String telephone, List<Student> children) {
        super(id, firstName, lastName);
        this.email = email;
        this.telephone = telephone;
        this.children = children;
    }

    public static ParentBuilder builder(){
        return new ParentBuilder();
    }
}
