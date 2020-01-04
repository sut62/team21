package se.g21.backend.enrollcoursesystem.repository;

import se.g21.backend.enrollcoursesystem.entities.EnrollCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EnrollCourseRepository extends JpaRepository<EnrollCourse, Long> {
    EnrollCourse findById(long id);
}
