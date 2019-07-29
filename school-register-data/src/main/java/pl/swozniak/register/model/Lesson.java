package pl.swozniak.register.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lessons")
public class Lesson extends BaseEntity{

    @Enumerated
    @Column(name = "number")
    @NotNull
    private LessonNumber number;

    @Enumerated
    @Column(name = "day_of_week")
    @NotNull
    private DayOfWeek dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private SchoolClass schoolClass;

    @Column(name = "topic")
    @Size(min = 2, max = 255)
    private String topic;

    @Column(name = "notes")
    private String notes;

    @ManyToMany
    @JoinTable(name = "absence",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> absent = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "unpreparedness",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> unprepared = new ArrayList<>();


    @Builder
    public Lesson(Long id, LessonNumber number, DayOfWeek dayOfWeek, SchoolClass schoolClass,
                  String topic, List<Student> absent, List<Student> unprepared, String notes) {
        super(id);
        this.number = number;
        this.dayOfWeek = dayOfWeek;
        this.schoolClass = schoolClass;
        this.topic = topic;
        this.absent = absent;
        this.unprepared = unprepared;
        this.notes = notes;
    }
}
