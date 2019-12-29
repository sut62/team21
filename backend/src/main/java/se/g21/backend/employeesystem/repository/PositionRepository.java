package se.g21.backend.employeesystem.repository;

import se.g21.backend.employeesystem.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findById(long id);
}