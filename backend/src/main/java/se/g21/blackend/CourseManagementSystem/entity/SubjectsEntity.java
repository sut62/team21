package se.g21.blackend.CourseManagementSystem.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="Subjects")
public class SubjectsEntity {
	@Id
	@SequenceGenerator(name="Subjects_seq",sequenceName="Subjects_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Subjects_seq")
	@Column(name="Subjects_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String name;

	// @OneToMany(fetch = FetchType.LAZY)
	// private Collection<CourseEntity> course;
}