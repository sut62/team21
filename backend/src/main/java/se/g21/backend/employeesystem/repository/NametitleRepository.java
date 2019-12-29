package se.g21.backend.employeesystem.repository;

import se.g21.backend.employeesystem.entities.Nametitle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NametitleRepository extends JpaRepository<Nametitle, Long> {
    public Nametitle findById(long employee);
}