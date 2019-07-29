package pl.swozniak.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swozniak.register.model.Grade;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findAllByStudent_Id(Long student_id);
}
