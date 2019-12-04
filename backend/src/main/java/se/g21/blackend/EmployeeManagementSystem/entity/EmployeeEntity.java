package se.g21.blackend.EmployeeManagementSystem.entity;

import lombok.*;
import lombok.NonNull;
import javax.persistence.*;
import java.util.Collection;
import com.fasterxml.jackson.annotation.*;

import se.g21.blackend.MainClass.entity.GenderEntity;
import se.g21.blackend.MainClass.entity.ProvinceEntity;

import se.g21.blackend.CourseManagementSystem.entity.CourseEntity;


@Data
@Entity
@NoArgsConstructor
@Table(name="Employee")
public class EmployeeEntity {

    @Id
    @SequenceGenerator(name="Employee_SEQ",sequenceName="Employee_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Employee_SEQ")
    @Column(name="Employee_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String name;
    private @NonNull String user_name;
    private @NonNull String password;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = GenderEntity.class)
    @JoinColumn(name = "Gender_ID", insertable = true)
    private GenderEntity gender;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ProvinceEntity.class)
    @JoinColumn(name = "Province_ID", insertable = true)
    private ProvinceEntity province;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PositionEntity.class)
    @JoinColumn(name = "Position_ID", insertable = true)
    private PositionEntity position;

    @OneToMany(fetch = FetchType.LAZY)
	private Collection<CourseEntity> course;

}