package se.g21.backend.recordexpensesystem.repository;

import se.g21.backend.recordexpensesystem.entities.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {
    ExpenseType findById(long id);
}