package se.g21.backend;

import se.g21.backend.recordexpensesystem.entities.*;
import se.g21.backend.recordexpensesystem.repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class ExpenseTypeTest {
    private Validator validator;

    @Autowired
    private ExpenseTypeRepository expenseTypeRepository;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void b6010331_testExpenseTypeSuccess(){
        System.out.println(
                "=============================== b6010331_testExpenseTypeSuccess ===============================");
        ExpenseType newExpenseType = new ExpenseType();
        newExpenseType.setType("รับเงินจากนักเรียน");
        newExpenseType = expenseTypeRepository.saveAndFlush(newExpenseType);

        Optional<ExpenseType> found = expenseTypeRepository.findById(newExpenseType.getId());
        assertEquals("รับเงินจากนักเรียน", found.get().getType());
    }

    @Test
    void b6010331_testExpenseTypeMustNotBeNull(){
        System.out.println(
                "=============================== b6010331_testExpenseTypeMustNotBeNull ===============================");
                ExpenseType newExpenseType = new ExpenseType();
                newExpenseType.setType(null);

        Set<ConstraintViolation<ExpenseType>> result = validator.validate(newExpenseType);
        //error ต้องมี 1 ค่าเท่านั้น
        assertEquals(1,result.size());

        ConstraintViolation<ExpenseType> v = result.iterator().next();
        assertEquals("must not be null",v.getMessage());
        assertEquals("type",v.getPropertyPath().toString());
    }
}
