package pl.swozniak.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.swozniak.register.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
