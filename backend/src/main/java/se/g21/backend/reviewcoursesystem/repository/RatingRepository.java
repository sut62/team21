package se.g21.backend.reviewcoursesystem.repository;
import se.g21.backend.reviewcoursesystem.entities.Rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface RatingRepository extends JpaRepository<Rating, Long> {
	Rating findById(long id);
}