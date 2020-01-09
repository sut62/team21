package se.g21.backend.employeesystem.entities;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.Date;

import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonFormat;
import se.g21.backend.coursesystem.entities.Course;
import se.g21.backend.studentsystem.entities.Student;
import se.g21.backend.enrollcoursesystem.entities.EnrollCourse;
import se.g21.backend.recordexpensesystem.entities.RecordExpense;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Employee")
public class Employee {
	@Id
	@SequenceGenerator(name = "Employee_seq", sequenceName = "Employee_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Employee_seq")
	@Column(name = "Employee_ID", unique = true, nullable = true)
	private @NonNull Long id;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Nametitle.class)
	@JoinColumn(name = "Nametitle_ID", insertable = true)
	private @NonNull Nametitle nametitle;

	@Column(name = "Fullname")
	private @NonNull String fullname;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
	@JoinColumn(name = "Gender_ID", insertable = true)
	private @NonNull Gender gender;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Position.class)
	@JoinColumn(name = "Position_ID", insertable = true)
	private @NonNull Position position;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
	@JoinColumn(name = "Province_ID", insertable = true)
	private @NonNull Province province;

	@Column(name = "Address")
	private @NonNull String address;

	@Column(name = "Username", unique = true)
	private @NonNull String username;

	@Column(name = "Password")
	private @NonNull String password;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "RecordDate")
	private @NonNull Date recorddate;

	@OneToMany(fetch = FetchType.LAZY)
	private Collection<Course> course;

	@OneToMany(fetch = FetchType.LAZY)
	private Collection<Student> student;

	@OneToMany(fetch = FetchType.LAZY)
	private Collection<EnrollCourse> enrollCourse;

	@OneToMany(fetch = FetchType.LAZY)
	private Collection<RecordExpense> recordExpense;
}
