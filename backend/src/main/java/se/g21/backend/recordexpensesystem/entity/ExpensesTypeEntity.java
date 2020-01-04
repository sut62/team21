package se.g21.backend.ExpensesManagementSystem.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name="EXPENSE_TYPE")

public class ExpenseType {
    @Id
    @SequenceGenerator(name="EXPENSE_TYPE_SEQ",sequenceName="EXPENSE_TYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EXPENSE_TYPE_SEQ")
    @Column(name="EXPENSE_TYPE_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String type;
    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Expense> expense;
}