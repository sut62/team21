package se.g21.backend.employeesystem.entities;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Position")
public class Position {
    @Id
    @SequenceGenerator(name = "Position_seq", sequenceName = "Position_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Position_seq")
    @Column(name = "Position_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name = "Position")
    private @NonNull String position;

    @Column(name = "Salary")
    private @NonNull Double salary;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Employee> employee;

}
