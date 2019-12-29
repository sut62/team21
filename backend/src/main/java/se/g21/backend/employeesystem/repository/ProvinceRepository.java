package se.g21.backend.employeesystem.repository;

import se.g21.backend.employeesystem.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Province findById(long id);
}