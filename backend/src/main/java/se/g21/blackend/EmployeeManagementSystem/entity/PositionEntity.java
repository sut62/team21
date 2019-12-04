package se.g21.blackend.EmployeeManagementSystem.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="Position")
public class PositionEntity {
	@Id
	@SequenceGenerator(name="Position_seq",sequenceName="Position_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Position_seq")
	@Column(name="Position_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String name;

	@OneToMany(fetch = FetchType.LAZY)
	private Collection<EmployeeEntity> employee;
}
