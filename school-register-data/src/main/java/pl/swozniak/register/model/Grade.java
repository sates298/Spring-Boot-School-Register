package pl.swozniak.register.model;

import lombok.*;
import pl.swozniak.register.model.enums.GradeValue;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "grades")
public class Grade extends BaseEntity {

    @Column(name = "grade")
    @NotNull
    @Enumerated
    private GradeValue grade;

//    @Column(name = "wage")
//    @NotNull
//    private Integer wage;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "notes")
    @Size(max = 255)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Builder
    public Grade(Long id, @NotNull GradeValue grade, Subject subject, @Size(max = 255) String notes, Student student) {
        super(id);
        this.grade = grade;
        this.subject = subject;
        this.notes = notes;
        this.student = student;
    }
}
