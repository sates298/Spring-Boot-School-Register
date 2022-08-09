package pl.swozniak.register.model;

import lombok.*;
import pl.swozniak.register.model.builders.StudentBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "students")
public class Student extends Person{

    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Grade> grades = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

//    @Builder
    public Student(Long id, String firstName, String lastName,
                   SchoolClass schoolClass, List<Grade> grades, Parent parent) {
        super(id, firstName, lastName);
        this.schoolClass = schoolClass;
        this.grades = grades;
        this.parent = parent;
    }

    public static StudentBuilder builder(){
        return new StudentBuilder();
    }
}
