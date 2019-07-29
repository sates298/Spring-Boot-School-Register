package pl.swozniak.register.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "grades")
public class Grade extends BaseEntity {

    @Column(name = "grade")
    @Min(0)
    @Max(6)
    @NotNull
    private Integer grade;

    @Column(name = "wage")
    @Min(1)
    @Max(6)
    @NotNull
    private Integer wage;

    @Column(name = "notes")
    @Size(min = 2, max = 255)
    @NotNull
    private String notes;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Builder
    public Grade(Long id, Integer grade, Integer wage, String notes, Student student) {
        super(id);
        this.grade = grade;
        this.wage = wage;
        this.notes = notes;
        this.student = student;
    }
}
