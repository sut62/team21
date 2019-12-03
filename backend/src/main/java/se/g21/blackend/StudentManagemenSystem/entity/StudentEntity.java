package se.g21.blackend.StudentManagemenSystem.entity;

import lombok.*;
import lombok.NonNull;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;

import se.g21.blackend.MainClass.entity.GenderEntity;

@Entity
@Data
@NoArgsConstructor
@Table(name="Student")
public class StudentEntity {

    @Id
    @SequenceGenerator(name="Student_SEQ",sequenceName="Student_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Student_SEQ")
    @Column(name="Student_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String name;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = GenderEntity.class)
    @JoinColumn(name = "Gender_ID", insertable = true)
    private GenderEntity gender;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = MajorEntity.class)
    @JoinColumn(name = "Major_ID", insertable = true)
    private MajorEntity major;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = EducationLevelEntity.class)
    @JoinColumn(name = "EducationLevel_ID", insertable = true)
    private EducationLevelEntity educationLevel;

}