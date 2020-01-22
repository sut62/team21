package se.g21.backend.enrollcoursesystem.entities;

import lombok.*;
import lombok.NonNull;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.*;
import java.time.LocalDateTime; 

import se.g21.backend.employeesystem.entities.Employee;
import se.g21.backend.studentsystem.entities.Student;
import se.g21.backend.coursesystem.entities.Course;

import java.util.Collection;
import se.g21.backend.reviewcoursesystem.entities.ReviewCourse;
import se.g21.backend.recordexpensesystem.entities.RecordExpense;

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
    @NotNull
    private Student student;  

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Course.class)
    @JoinColumn(name = "Course_ID", insertable = true)
    @NotNull
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Computer.class)
    @JoinColumn(name = "Computer_ID", insertable = true)
    @NotNull
    private Computer computer;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "Employee_ID", insertable = true)
    @NotNull
    private Employee employee;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @Column(name = "enroll_date")
    @NotNull
    private  LocalDateTime date;

    @Size(min=1,max=50)
    @Column(name = "note")
    @NotNull
    private String note;

    @OneToMany(fetch = FetchType.LAZY)
	private Collection<ReviewCourse> reviewCourse;

    @OneToMany(fetch = FetchType.LAZY)
	private Collection<RecordExpense> recordExpense;
}