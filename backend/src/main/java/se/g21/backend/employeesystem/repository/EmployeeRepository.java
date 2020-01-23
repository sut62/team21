package se.g21.backend.employeesystem.repository;

import se.g21.backend.employeesystem.entities.Employee;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findById(long id);

    @Query(value = "SELECT * FROM EMPLOYEE WHERE FULLNAME LIKE :fullname ", nativeQuery = true)
    Collection<Employee> findByFullname(@Param("fullname") String fullname);
}