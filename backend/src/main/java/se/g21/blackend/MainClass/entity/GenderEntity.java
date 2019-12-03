package se.g21.blackend.MainClass.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Collection;

import se.g21.blackend.StudentManagemenSystem.entity.StudentEntity;

@Data
@Entity
@NoArgsConstructor
@Table(name="Gender")
public class GenderEntity {
	@Id
	@SequenceGenerator(name="Gender_seq",sequenceName="Gender_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Gender_seq")
	@Column(name="Gender_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String name;

	@OneToMany(fetch = FetchType.LAZY)
	private Collection<StudentEntity> student;
}
