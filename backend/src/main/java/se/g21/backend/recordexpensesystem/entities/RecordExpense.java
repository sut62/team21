package se.g21.backend.recordexpensesystem.entities;
import org.springframework.beans.factory.annotation.Autowired;
import se.g21.backend.employeesystem.entities.Employee;
import se.g21.backend.enrollcoursesystem.entities.EnrollCourse;

import lombok.*;
import javax.persistence.*;
import lombok.NonNull;
import java.util.Collection;
import com.fasterxml.jackson.annotation.*;
import se.g21.backend.recordexpensesystem.repository.ExpenseTypeRepository;

import java.time.LocalDateTime;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="RECORD_EXPENSE")
public class RecordExpense {
    @Id
    @SequenceGenerator(name="RECORD_EXPENSE_SEQ",sequenceName="RECORD_EXPENSE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RECORD_EXPENSE_SEQ")
    
    @Column(name="RECORD_EXPENSE_ID",unique = true, nullable = true)
    private Long id;   
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ExpenseType.class)
    @JoinColumn(name = "EXPENSE_TYPE_ID", insertable = true)
    @NotNull        
    private ExpenseType expenseType;

    @Column(name="BUDGET",nullable = true)
    @Max(50000)     
    @Positive
    @NotNull
    private double budget;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee rec;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = EnrollCourse.class)
    @JoinColumn(name = "EnrollCourse_ID", insertable = true)
    private EnrollCourse enrollCourse;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+7")
    @NotNull        
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "CREATEDBY", insertable = true)
    @NotNull        
    private Employee createdBy;

    @PrePersist
    void prePersist(){
       if(this.expenseType.getId() == 1){
           this.rec = null;
       }else if(this.expenseType.getId() == 2){
           this.enrollCourse = null;
       }
    }
}