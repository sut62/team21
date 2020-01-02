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
    
    
    public void setFullname(String fullname){
        this.fullname=fullname;
    }

    public void setTel(String tel){
        this.tel=tel;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public void setOld(Long old){
        this.old=old;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "Gender_ID", insertable = true)
    private @NonNull Gender gender;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "Province_ID", insertable = true)
    private @NonNull Province province;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Nametitle.class)
    @JoinColumn(name = "Nametitle_ID", insertable = true)
    private @NonNull Nametitle nametitle;

    public void setUsername(String username){
        this.username=username;
    }

    public void setPassword(String password){
        this.password=password;
    }    

    public void setAddress(String address){
        this.address=address;
    }
    
     public void setRegDate(Date date){
        this.date=date;
    }
}