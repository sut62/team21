package se.g21.backend.studentsystem.repository;

import se.g21.backend.studentsystem.entities.Student;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findById(long id);

    @Query(value = "SELECT * FROM STUDENT WHERE FULLNAME LIKE :fullname ", nativeQuery = true)
    Collection<Student> findByFullname(@Param("fullname") String fullname);
}