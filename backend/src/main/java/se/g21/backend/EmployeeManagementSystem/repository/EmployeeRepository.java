package se.g21.backend.EmployeeManagementSystem.repository;

import se.g21.backend.EmployeeManagementSystem.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}