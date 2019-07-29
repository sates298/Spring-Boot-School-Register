package pl.swozniak.register.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "classes")
public class SchoolClass extends BaseEntity {

    @OneToMany(mappedBy = "schoolClass")
    private List<Student> students = new ArrayList<>();

    @Column(name = "character")
    @NotNull
    private Character character;

    @Column(name = "level")
    @Min(1)
    @Max(8)
    private Integer level;

    @Builder
    public SchoolClass(Long id, List<Student> students, Character character, Integer level) {
        super(id);
        this.students = students;
        this.character = character;
        this.level = level;
    }

    @Override
    public String toString(){
        return level.toString() + character;
    }
}
