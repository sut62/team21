package se.g21.backend.reviewcoursesystem.repository;

import se.g21.backend.reviewcoursesystem.entities.ReviewCourse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ReviewCourseRepository extends JpaRepository<ReviewCourse, Long> {
    ReviewCourse findById(long id);
}