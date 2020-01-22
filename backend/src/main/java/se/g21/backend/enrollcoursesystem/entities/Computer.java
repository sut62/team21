package se.g21.backend.enrollcoursesystem.entities;

import lombok.*;
import lombok.NonNull;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import se.g21.backend.coursesystem.entities.Room;

import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="Computer")
public class Computer {

    @Id
    @SequenceGenerator(name="Computer_SEQ",sequenceName="Computer_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Computer_SEQ")
    @Column(name="Computer_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @Pattern(regexp = "[P][C][0-9][0-9]")
    @NotNull
    private String pcNumber;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    @JoinColumn(name = "Room_ID", insertable = true)
    @NotNull
    private Room room;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<EnrollCourse> enrollCourse;

}