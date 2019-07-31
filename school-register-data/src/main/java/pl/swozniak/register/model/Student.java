package pl.swozniak.register.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "students")
public class Student extends Person{

    @Column(name = "number")
    @Min(1)
    @Max(35)
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Grade> grades = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @Builder
    public Student(Long id, String firstName, String lastName, @Min(1) @Max(35) Integer number, SchoolClass schoolClass, List<Grade> grades, Parent parent) {
        super(id, firstName, lastName);
        this.number = number;
        this.schoolClass = schoolClass;
        this.grades = grades;
        this.parent = parent;
    }
}
