package se.g21.blackend.CourseManagementSystem.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="Day")
public class DayEntity {
	@Id
	@SequenceGenerator(name="Day_seq",sequenceName="Day_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Day_seq")
	@Column(name="Day_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String name;

	@OneToMany(fetch = FetchType.LAZY)
	private Collection<CourseEntity> course;
}