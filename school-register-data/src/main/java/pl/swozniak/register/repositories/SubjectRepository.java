package pl.swozniak.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.swozniak.register.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
