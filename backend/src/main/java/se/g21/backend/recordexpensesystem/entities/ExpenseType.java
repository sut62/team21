package se.g21.backend.recordexpensesystem.entities;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull
    private String type;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<RecordExpense> recordExpense;

}