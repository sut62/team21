package se.g21.backend.enrollcoursesystem.repository;

import se.g21.backend.enrollcoursesystem.entities.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ComputerRepository extends JpaRepository<Computer, Long> {
    Computer findById(long id);
}
