package se.g21.backend.coursesystem.repository;

import se.g21.backend.coursesystem.entities.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TimeRepository extends JpaRepository<Time, Long> {
    Time findById(long id);
}
