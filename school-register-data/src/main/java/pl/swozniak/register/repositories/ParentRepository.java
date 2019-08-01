package pl.swozniak.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.swozniak.register.model.Parent;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
}
