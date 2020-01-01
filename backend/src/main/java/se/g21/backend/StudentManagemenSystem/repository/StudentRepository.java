package se.g21.backend.StudentManagemenSystem.repository;

import se.g21.backend.StudentManagemenSystem.entity.StudentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}