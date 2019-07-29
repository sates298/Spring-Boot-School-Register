package pl.swozniak.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.swozniak.register.model.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
