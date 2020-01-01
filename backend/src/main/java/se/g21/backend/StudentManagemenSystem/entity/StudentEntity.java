package se.g21.backend.StudentManagemenSystem.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import se.g21.backend.employeesystem.entities.Nametitle;
import se.g21.backend.employeesystem.entities.Gender;
import se.g21.backend.employeesystem.entities.Province;

@Data
@Entity
@NoArgsConstructor
@Table(name="Student")
public class StudentEntity {

    @Id
    @SequenceGenerator(name="student_seq",sequenceName="student_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="student_seq")
    @Column(name = "STUDENT_ID", unique = true, nullable = true)
    private @NonNull Long id;
    
    @Column(name = "Fullname")
    private @NonNull String fullname;

    @Column(name = "Tel")
    private @NonNull String tel;

    @Column(name = "Email")
    private @NonNull String email;

    @Column(name = "Old")
    private @NonNull Long old;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "Gender_ID", insertable = true)
    private @NonNull Gender gender;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "Province_ID", insertable = true)
    private @NonNull Province province;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Nametitle.class)
    @JoinColumn(name = "Nametitle_ID", insertable = true)
    private @NonNull Nametitle nametitle;

    @Column(name = "Username")
    private @NonNull String username;

    @Column(name = "Password")
    private @NonNull String password;

    @Column(name = "Address")
    private @NonNull String address;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name="REG_DATE")
    private @NonNull Date regDate;

}