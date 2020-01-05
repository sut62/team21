package se.g21.backend.recordexpensesystem.repository;

import se.g21.backend.recordexpensesystem.entities.RecordExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RecordExpenseRepository extends JpaRepository<RecordExpense, Long> {
    RecordExpense findById(long id);
}
