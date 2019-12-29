package se.g21.backend.employeesystem.repository;

import se.g21.backend.employeesystem.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GenderRepository extends JpaRepository<Gender, Long> {
    Gender findById(long id);
}