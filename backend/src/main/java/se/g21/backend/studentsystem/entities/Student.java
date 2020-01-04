package se.g21.backend.studentsystem.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;



import se.g21.backend.employeesystem.entities.*;

import se.g21.backend.enrollcoursesystem.entities.EnrollCourse;

@Data
@Entity
@NoArgsConstructor
@Table(name="Student")
public class Student {

    @Id
    @SequenceGenerator(name="student_seq",sequenceName="student_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="student_seq")
    @Column(name = "STUDENT_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Nametitle.class)
	@JoinColumn(name = "Nametitle_ID", insertable = true)
    private @NonNull Nametitle nametitle;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "Gender_ID", insertable = true)
    private @NonNull Gender gender;
    
    @Column(name = "fullname")
    private @NonNull String fullname;
    
    @Column(name = "Username",unique = true)
    private @NonNull String username;

    @Column(name = "password")
    private @NonNull String password;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "Province_ID", insertable = true)
    private @NonNull Province province;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
	@JoinColumn(name = "Employee_ID", insertable = true)
    private @NonNull Employee employee;
    
    @OneToMany(fetch = FetchType.LAZY)
	private Collection<EnrollCourse> enrollCourse;


}