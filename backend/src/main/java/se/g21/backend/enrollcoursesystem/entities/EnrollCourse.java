package se.g21.backend.enrollcoursesystem.entities;

import lombok.*;
import lombok.NonNull;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import java.time.LocalDateTime; 
// import java.util.Collection;
// import com.fasterxml.jackson.annotation.*;

import se.g21.backend.employeesystem.entities.Employee;

import se.g21.backend.studentsystem.entities.Student;

import se.g21.backend.coursesystem.entities.Course;


@Data
@Entity
@NoArgsConstructor
@Table(name="EnrollCourse")
public class EnrollCourse {

    @Id
    @SequenceGenerator(name="EnrollCourse_SEQ",sequenceName="EnrollCourse_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EnrollCourse_SEQ")
    @Column(name="EnrollCourse_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Student.class)
    @JoinColumn(name = "Student_ID", insertable = true)
    private Student student;  

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Course.class)
    @JoinColumn(name = "Course_ID", insertable = true)
    private Course course;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "Employee_ID", insertable = true)
    private Employee employee;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    private @NonNull LocalDateTime date;


}