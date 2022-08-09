package pl.swozniak.register.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.swozniak.register.model.builders.SubjectBuilder;
import pl.swozniak.register.model.enums.SubjectName;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "subjects")
public class Subject extends BaseEntity {

    @Column(name = "name")
    @Enumerated
    private SubjectName name;

    @OneToMany(mappedBy = "subject")
    private List<Teacher> teachers;

//    @Builder
    public Subject(Long id, SubjectName name, List<Teacher> teachers) {
        super(id);
        this.name = name;
        this.teachers = teachers;
    }

    public static SubjectBuilder builder(){
        return new SubjectBuilder();
    }
}
