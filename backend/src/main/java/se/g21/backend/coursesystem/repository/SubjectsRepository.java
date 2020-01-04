package se.g21.backend.coursesystem.repository;

import se.g21.backend.coursesystem.entities.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SubjectsRepository extends JpaRepository<Subjects, Long> {
    Subjects findById(long id);
}
