package se.g21.blackend.CourseManagementSystem.entity;

import lombok.*;
import lombok.NonNull;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;

import java.util.Date;

import se.g21.blackend.EmployeeManagementSystem.entity.EmployeeEntity;

@Entity
@Data
@NoArgsConstructor
@Table(name="Course")
public class CourseEntity {

    @Id
    @SequenceGenerator(name="Course_SEQ",sequenceName="Course_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Course_SEQ")
    @Column(name="Course_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SubjectsEntity.class)
    @JoinColumn(name = "Subjects_ID", insertable = true)
    private SubjectsEntity subjects;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = EmployeeEntity.class)
    @JoinColumn(name = "Employee_ID", insertable = true)
    private EmployeeEntity employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomEntity.class)
    @JoinColumn(name = "Room_ID", insertable = true)
    private RoomEntity room;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DayEntity.class)
    @JoinColumn(name = "Day_ID", insertable = true)
    private DayEntity day;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "GMT+7")
    private @NonNull Date start_time;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "GMT+7")
    private @NonNull Date end_time;

}