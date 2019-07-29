package pl.swozniak.register.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student extends BaseEntity{

    @Column(name = "first_name")
    @NotBlank
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @NotBlank
    private String lastName;

    @Column(name = "number")
    @Min(1)
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    @ManyToMany(mappedBy = "absent")
    private List<Lesson> absence = new ArrayList<>();

    @ManyToMany(mappedBy = "unprepared")
    private List<Lesson> unpreparedness = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Grade> grades = new ArrayList<>();

    @Builder
    public Student(Long id, String firstName, String lastName, Integer number,
                   SchoolClass schoolClass, List<Lesson> absence, List<Lesson> unpreparedness, List<Grade> grades) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.schoolClass = schoolClass;
        this.absence = absence;
        this.unpreparedness = unpreparedness;
        this.grades = grades;
    }
}
