package se.g21.backend.reviewcourse.repository;

import se.g21.backend.reviewcourse.entities.ReviewCourse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ReviewCourseRepository extends JpaRepository<ReviewCourse, Long> {
}