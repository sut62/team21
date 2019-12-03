package se.g21.blackend.StudentManagemenSystem.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="Major")
public class MajorEntity {
    @Id
    @SequenceGenerator(name="major_seq",sequenceName="major_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="major_seq")
    @Column(name="Major_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String name;

    @OneToMany(fetch = FetchType.LAZY)
	private Collection<StudentEntity> student;
}