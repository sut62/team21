package se.g21.backend.coursesystem.entities;

import lombok.*;

import javax.persistence.*;

import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="Subjects")
public class Subjects {
	@Id
	@SequenceGenerator(name="Subjects_seq",sequenceName="Subjects_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Subjects_seq")
	@Column(name="Subjects_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String subjectsName;

	@OneToMany(fetch = FetchType.LAZY)
	private Collection<Course> course;
}