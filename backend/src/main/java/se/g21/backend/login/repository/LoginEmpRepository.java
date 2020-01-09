package se.g21.backend.login.repository;

import se.g21.backend.employeesystem.entities.Employee;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.repository.query.Param;
import java.util.Collection;
@CrossOrigin(origins = "http://localhost:8080")
@RepositoryRestResource
public interface LoginEmpRepository extends JpaRepository<Employee, Long> {
    @Query( value = "SELECT * FROM EMPLOYEE WHERE USERNAME = :user AND PASSWORD = :pass ",
            nativeQuery = true)
    Collection<Employee> findEmpAccount(@Param("user") String user,@Param("pass") String pass);
}