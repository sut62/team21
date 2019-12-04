package se.g21.blackend.MainClass.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Collection;

import se.g21.blackend.EmployeeManagementSystem.entity.EmployeeEntity;

@Data
@Entity
@NoArgsConstructor
@Table(name="Province")
public class ProvinceEntity {
	@Id
	@SequenceGenerator(name="Province_seq",sequenceName="Province_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Province_seq")
	@Column(name="Province_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String name;

	@OneToMany(fetch = FetchType.LAZY)
	private Collection<EmployeeEntity> employee;
}
