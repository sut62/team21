package se.g21.blackend.StudentManagemenSystem.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="EducationLevel")
public class EducationLevelEntity {
	@Id
	@SequenceGenerator(name="EducationLevel_seq",sequenceName="EducationLevel_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EducationLevel_seq")
	@Column(name="EducationLevel_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String name;

	@OneToMany(fetch = FetchType.LAZY)
	private Collection<StudentEntity> student;
}
