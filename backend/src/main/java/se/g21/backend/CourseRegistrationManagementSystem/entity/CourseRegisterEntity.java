package se.g21.backend.CourseRegistrationManagementSystem.entity;

import lombok.*;
import lombok.NonNull;
import javax.persistence.*;
// import java.util.Collection;
// import com.fasterxml.jackson.annotation.*;

import se.g21.backend.StudentManagemenSystem.entity.StudentEntity;
import se.g21.backend.CourseManagementSystem.entity.CourseEntity;
import se.g21.backend.EmployeeManagementSystem.entity.EmployeeEntity;


@Data
@Entity
@NoArgsConstructor
@Table(name="CourseRegister")
public class CourseRegisterEntity {

    @Id
    @SequenceGenerator(name="CourseRegister_SEQ",sequenceName="CourseRegister_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CourseRegister_SEQ")
    @Column(name="CourseRegister_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = StudentEntity.class)
    @JoinColumn(name = "Student_ID", insertable = true)
    private StudentEntity student;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CourseEntity.class)
    @JoinColumn(name = "Course_ID", insertable = true)
    private CourseEntity course;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = EmployeeEntity.class)
    @JoinColumn(name = "Employee_ID", insertable = true)
    private EmployeeEntity employee;


}