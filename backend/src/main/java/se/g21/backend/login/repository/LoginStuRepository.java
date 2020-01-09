package se.g21.backend.login.repository;

import se.g21.backend.studentsystem.entities.Student;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.repository.query.Param;
import java.util.Collection;
@CrossOrigin(origins = "http://localhost:8080")
@RepositoryRestResource
public interface LoginStuRepository extends JpaRepository<Student, Long> {

    @Query( value = "SELECT * FROM STUDENT  WHERE USERNAME = :user AND PASSWORD = :pass ",
        nativeQuery = true)
    Collection<Student> findStuAccount(@Param("user") String user,@Param("pass") String pass);
}

// Collection<Student> findStuAccount(@Param("user") String user,@Param("pass") String pass);