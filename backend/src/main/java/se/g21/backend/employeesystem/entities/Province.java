package se.g21.backend.employeesystem.entities;

import lombok.*;
import se.g21.backend.studentsystem.entities.Student;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Province")
public class Province {
    @Id
    @SequenceGenerator(name = "Province_seq", sequenceName = "Province_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Province_seq")
    @Column(name = "Province_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @NotNull
    @Column(name = "Province")
    private String province;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Employee> employee;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Student> student;
}
