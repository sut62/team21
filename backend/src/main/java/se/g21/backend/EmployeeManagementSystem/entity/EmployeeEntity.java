package se.g21.backend.EmployeeManagementSystem.entity;

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
@Table(name = "Employee")
public class EmployeeEntity {
	@Id
	@SequenceGenerator(name = "Employee_seq", sequenceName = "Employee_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Employee_seq")
	@Column(name = "Employee_ID", unique = true, nullable = true)
    private @NonNull Long id;
    
	@Column(name = "Title_ID", nullable = true)
    private @NonNull Title title;
    
    private @NonNull String firstname;
    
	private @NonNull String lastname;
    @Column(name = "Gender_ID", nullable = true)
	private @NonNull Gender gender;
}
