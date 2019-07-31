package pl.swozniak.register.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.swozniak.register.model.enums.SubjectName;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "subjects")
public class Subject extends BaseEntity {

    @Column(name = "name")
    @Enumerated
    private SubjectName subject;

    @Builder
    public Subject(Long id, SubjectName subject) {
        super(id);
        this.subject = subject;
    }
}
