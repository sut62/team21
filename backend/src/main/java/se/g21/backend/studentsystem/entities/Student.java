package se.g21.backend.studentsystem.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;
import javax.validation.constraints.*;

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
    @NotNull
    private  Nametitle nametitle;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "Gender_ID", insertable = true)
    @NotNull
    private  Gender gender;
    
    @Column(name = "fullname")
    @NotNull
    private  String fullname;
    
    @Column(name = "Username",unique = true)
    @NotNull
    private  String username;

    @Column(name = "password")
    @NotNull
    private  String password;

    @Column(name = "Address")
    @NotNull
    private  String address;

    @Positive
    @NotNull
    @Column(name = "Old")
    private  long old;

    @Column(name = "Tel")
    @NotNull
    private  String tel;

    @Column(name = "Email")
    @Email
    @NotNull
    private  String email;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "Province_ID", insertable = true)
    @NotNull
    private  Province province;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
	@JoinColumn(name = "Employee_ID", insertable = true)
    @NotNull
    private  Employee employee;
    
    @OneToMany(fetch = FetchType.LAZY)
	private Collection<EnrollCourse> enrollCourse;


}