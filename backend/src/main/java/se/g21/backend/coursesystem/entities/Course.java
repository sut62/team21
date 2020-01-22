package se.g21.backend.coursesystem.entities;

import lombok.*;
import lombok.NonNull;
import javax.persistence.*;
import java.util.Collection;


import se.g21.backend.employeesystem.entities.Employee;

import se.g21.backend.enrollcoursesystem.entities.EnrollCourse;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="Course")
public class Course {

    @Id
    @SequenceGenerator(name="Course_SEQ",sequenceName="Course_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Course_SEQ")
    @Column(name="Course_ID",unique = true, nullable = true)
    private Long id;
    @NotNull
    @Size(max=30)
    private  String courseName;
    @Positive
    @NotNull
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Subjects.class)
    @JoinColumn(name = "Subjects_ID", insertable = true)
    @NotNull
    private Subjects subjects;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    @JoinColumn(name = "Room_ID", insertable = true)
    @NotNull
    private Room room;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Time.class)
    @JoinColumn(name = "Time_ID", insertable = true)
    @NotNull
    private Time time;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "Employee_ID", insertable = true)
    @NotNull
    private Employee employee;

    @OneToMany(fetch = FetchType.LAZY)
	private Collection<EnrollCourse> enrollCourse;




}