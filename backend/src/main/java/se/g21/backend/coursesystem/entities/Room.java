package se.g21.backend.coursesystem.entities;

import lombok.*;

import javax.persistence.*;

import java.util.Collection;

import se.g21.backend.enrollcoursesystem.entities.Computer;

@Data
@Entity
@NoArgsConstructor
@Table(name="Room")
public class Room {
	@Id
	@SequenceGenerator(name="Room_seq",sequenceName="Room_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Room_seq")
	@Column(name="Room_ID",unique = true, nullable = true)
	private @NonNull Long id;
	private @NonNull String room;

	@OneToMany(fetch = FetchType.LAZY)
	private Collection<Course> course;

	@OneToMany(fetch = FetchType.LAZY)
	private Collection<Computer> computer;
	
}