package se.g21.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import se.g21.backend.enrollcoursesystem.entities.*;
import se.g21.backend.enrollcoursesystem.repository.*;

import se.g21.backend.employeesystem.entities.*;
import se.g21.backend.employeesystem.repository.*;

import se.g21.backend.recordexpensesystem.entities.*;
import se.g21.backend.recordexpensesystem.repository.*;

//@DataJpaTest
@SpringBootTest
public class RecordExpenseTest {

    private Validator validator;

    @Autowired
    private EnrollCourseRepository enrollCourseRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RecordExpenseRepository recordExpenseRepository;

    @Autowired
    private ExpenseTypeRepository expenseTypeRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6010331_testRecordExpenseSuccess() {
        System.out.println("========== b6010331_testRecordExpenseSuccess ==========");

// ---- SAVE Student --- 
        RecordExpense newRecordStu =  new RecordExpense();

        ExpenseType expenseTypeStu = expenseTypeRepository.findById(1);
        newRecordStu.setExpenseType(expenseTypeStu);

        newRecordStu.setRec(null);  

        EnrollCourse enrollCourse = enrollCourseRepository.findById(1);
        newRecordStu.setEnrollCourse(enrollCourse);

        Double budget = enrollCourse.getCourse().getPrice();
 
        newRecordStu.setBudget(budget);
        
        DateTimeFormatter dateFormatStu = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateStu = LocalDateTime.parse((String)"2019-08-12 10:12:56",dateFormatStu);
        newRecordStu.setDate(dateStu);
        
        Employee createdByStu = employeeRepository.findById(6);
        newRecordStu.setCreatedBy(createdByStu);

        recordExpenseRepository.saveAndFlush(newRecordStu);

        
        Optional<RecordExpense> FindnewRecordStu = recordExpenseRepository.findById(newRecordStu.getId());

        assertEquals(expenseTypeStu.getType(),  FindnewRecordStu.get().getExpenseType().getType());
        assertEquals(null,  FindnewRecordStu.get().getRec());
        assertEquals(enrollCourse.getCourse().getPrice(),  FindnewRecordStu.get().getBudget());
        assertEquals(dateStu.format(dateFormatStu),  FindnewRecordStu.get().getDate().format(dateFormatStu));
        assertEquals(createdByStu.getFullname(), FindnewRecordStu.get().getCreatedBy().getFullname());

// ---- SAVE Employee --- 
        RecordExpense newRecordEmp =  new RecordExpense();

        ExpenseType expenseTypeEmp = expenseTypeRepository.findById(2);
        newRecordEmp.setExpenseType(expenseTypeEmp);

        Employee rec = employeeRepository.findById(5);
        newRecordEmp.setRec(rec);  

        newRecordEmp.setEnrollCourse(null);
        
        newRecordEmp.setBudget(rec.getPosition().getSalary());
        
        DateTimeFormatter dateFormatEmp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateEmp = LocalDateTime.parse((String)"2019-08-12 10:12:56",dateFormatEmp);
        newRecordEmp.setDate(dateEmp);
        
        Employee createdByEmp = employeeRepository.findById(6);
        newRecordEmp.setCreatedBy(createdByEmp);

        recordExpenseRepository.saveAndFlush(newRecordEmp);
        

        Optional<RecordExpense> FindnewRecordEmp = recordExpenseRepository.findById(newRecordEmp.getId());

        assertEquals(expenseTypeEmp.getType(),FindnewRecordEmp.get().getExpenseType().getType());
        assertEquals(null,FindnewRecordEmp.get().getEnrollCourse());
        assertEquals(rec.getFullname(),FindnewRecordEmp.get().getRec().getFullname());
        assertEquals(rec.getPosition().getSalary(),FindnewRecordEmp.get().getBudget());
        assertEquals(dateEmp.format(dateFormatEmp),FindnewRecordEmp.get().getDate().format(dateFormatEmp));
        assertEquals(createdByEmp.getFullname(),FindnewRecordEmp.get().getCreatedBy().getFullname());       


    }

    boolean isValidDateForMat(String date) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            df.setLenient(false);
            try {
               df.parse(date);
            } catch (ParseException e) {
               return false;
            }
            return true;
    }

    @Test
    void b6010331_testDateFormat() {
        System.out.println("========== b6010331_testDateFormat ==========");

// ---- SAVE Student --- 
        RecordExpense newRecordStu =  new RecordExpense();

        ExpenseType expenseTypeStu = expenseTypeRepository.findById(1);
        newRecordStu.setExpenseType(expenseTypeStu);

        newRecordStu.setRec(null);  

        EnrollCourse enrollCourse = enrollCourseRepository.findById(1);
        newRecordStu.setEnrollCourse(enrollCourse);
 
        newRecordStu.setBudget(enrollCourse.getCourse().getPrice());
        
        DateTimeFormatter dateFormatStu = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateStu = LocalDateTime.parse((String)"2019-08-12 10:12:56",dateFormatStu);
        newRecordStu.setDate(dateStu);
        
        Employee createdByStu = employeeRepository.findById(6);
        newRecordStu.setCreatedBy(createdByStu);

        recordExpenseRepository.saveAndFlush(newRecordStu);

// ---- SAVE Employee --- 
        RecordExpense newRecordEmp =  new RecordExpense();

        ExpenseType expenseTypeEmp = expenseTypeRepository.findById(2);
        newRecordEmp.setExpenseType(expenseTypeEmp);

        Employee rec = employeeRepository.findById(5);
        newRecordEmp.setRec(rec);  
        
        newRecordEmp.setEnrollCourse(null);
        
        newRecordEmp.setBudget(rec.getPosition().getSalary());
        
        DateTimeFormatter dateFormatEmp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateEmp = LocalDateTime.parse((String)"2019-08-12 10:12:56",dateFormatEmp);
        newRecordEmp.setDate(dateEmp);
        
        Employee createdByEmp = employeeRepository.findById(6);
        newRecordEmp.setCreatedBy(createdByEmp);

        recordExpenseRepository.saveAndFlush(newRecordEmp);


        Optional<RecordExpense> FindnewRecordStu = recordExpenseRepository.findById(newRecordStu.getId());

        assertEquals(expenseTypeStu.getType(),  FindnewRecordStu.get().getExpenseType().getType());
        assertEquals(null,  FindnewRecordStu.get().getRec());
        assertEquals(enrollCourse.getCourse().getPrice(),  FindnewRecordStu.get().getBudget());
        assertTrue(isValidDateForMat(FindnewRecordStu.get().getDate().format(dateFormatStu)));
        assertEquals(createdByStu.getFullname(), FindnewRecordStu.get().getCreatedBy().getFullname());
        

       Optional<RecordExpense> FindnewRecordEmp = recordExpenseRepository.findById(newRecordEmp.getId());

        assertEquals(expenseTypeEmp.getType(),FindnewRecordEmp.get().getExpenseType().getType());
        assertEquals(null,FindnewRecordEmp.get().getEnrollCourse());
        assertEquals(rec.getFullname(),FindnewRecordEmp.get().getRec().getFullname());
        assertEquals(rec.getPosition().getSalary(),FindnewRecordEmp.get().getBudget());
        assertTrue(isValidDateForMat(FindnewRecordEmp.get().getDate().format(dateFormatEmp)));
        assertEquals(createdByEmp.getFullname(),FindnewRecordEmp.get().getCreatedBy().getFullname());       
    }
    
    @Test
    void b6010331_testCreatedByNotBeNull() {
        System.out.println(
                "========== b6010331_testCreatedByNotBeNull ==========");

// ---- SAVE Student --- 
        RecordExpense newRecordStu =  new RecordExpense();

        ExpenseType expenseTypeStu = expenseTypeRepository.findById(1);
        newRecordStu.setExpenseType(expenseTypeStu);

        newRecordStu.setRec(null);  

        EnrollCourse enrollCourse = enrollCourseRepository.findById(1);
        newRecordStu.setEnrollCourse(enrollCourse);
 
        newRecordStu.setBudget(enrollCourse.getCourse().getPrice());
        
        DateTimeFormatter dateFormatStu = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateStu = LocalDateTime.parse((String)"2019-08-12 10:12:56",dateFormatStu);
        newRecordStu.setDate(dateStu);
        
        Employee createdByStu = employeeRepository.findById(6);
        newRecordStu.setCreatedBy(null);      

// ---- SAVE Employee --- 
        RecordExpense newRecordEmp =  new RecordExpense();

        ExpenseType expenseTypeEmp = expenseTypeRepository.findById(2);
        newRecordEmp.setExpenseType(expenseTypeEmp);

        Employee rec = employeeRepository.findById(5);
        newRecordEmp.setRec(rec);  

        newRecordEmp.setEnrollCourse(null);
 
        newRecordEmp.setBudget(rec.getPosition().getSalary());
        
        DateTimeFormatter dateFormatEmp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateEmp = LocalDateTime.parse((String)"2019-08-12 10:12:56",dateFormatEmp);
        newRecordEmp.setDate(dateEmp);
        
        Employee createdByEmp = employeeRepository.findById(6);
        newRecordEmp.setCreatedBy(null);
    
        Set<ConstraintViolation<RecordExpense>> resultStu = validator.validate(newRecordStu);
        assertEquals(1, resultStu.size());
        ConstraintViolation<RecordExpense> vStu = resultStu.iterator().next();
        assertEquals("must not be null", vStu.getMessage());
        assertEquals("createdBy", vStu.getPropertyPath().toString());

        Set<ConstraintViolation<RecordExpense>> resultEmp = validator.validate(newRecordEmp);
        assertEquals(1, resultEmp.size());
        ConstraintViolation<RecordExpense> vEmp = resultEmp.iterator().next();
        assertEquals("must not be null", vEmp.getMessage());
        assertEquals("createdBy", vEmp.getPropertyPath().toString());
    }
   
    @Test
    void b6010331_testDateNotBeNull() {
        System.out.println(
                "========== b6010331_testDateNotBeNull ==========");

// ---- SAVE Student --- 
        RecordExpense newRecordStu =  new RecordExpense();

        ExpenseType expenseTypeStu = expenseTypeRepository.findById(1);
        newRecordStu.setExpenseType(expenseTypeStu);

        newRecordStu.setRec(null);  

        EnrollCourse enrollCourse = enrollCourseRepository.findById(1);
        newRecordStu.setEnrollCourse(enrollCourse);
 
        newRecordStu.setBudget(enrollCourse.getCourse().getPrice());
        
        DateTimeFormatter dateFormatStu = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateStu = LocalDateTime.parse((String)"2019-08-12 10:12:56",dateFormatStu);
        newRecordStu.setDate(null);
        
        Employee createdByStu = employeeRepository.findById(6);
        newRecordStu.setCreatedBy(createdByStu);    

// ---- SAVE Employee --- 
        RecordExpense newRecordEmp =  new RecordExpense();

        ExpenseType expenseTypeEmp = expenseTypeRepository.findById(2);
        newRecordEmp.setExpenseType(expenseTypeEmp);

        Employee rec = employeeRepository.findById(5);
        newRecordEmp.setRec(rec);  

        newRecordEmp.setEnrollCourse(null);
 
        newRecordEmp.setBudget(rec.getPosition().getSalary());
        
        DateTimeFormatter dateFormatEmp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateEmp = LocalDateTime.parse((String)"2019-08-12 10:12:56",dateFormatEmp);
        newRecordEmp.setDate(null);
        
        Employee createdByEmp = employeeRepository.findById(6);
        newRecordEmp.setCreatedBy(createdByEmp);
    
        Set<ConstraintViolation<RecordExpense>> resultStu = validator.validate(newRecordStu);
        assertEquals(1, resultStu.size());
        ConstraintViolation<RecordExpense> vStu = resultStu.iterator().next();
        assertEquals("must not be null", vStu.getMessage());
        assertEquals("date", vStu.getPropertyPath().toString());

        Set<ConstraintViolation<RecordExpense>> resultEmp = validator.validate(newRecordEmp);
        assertEquals(1, resultEmp.size());
        ConstraintViolation<RecordExpense> vEmp = resultEmp.iterator().next();
        assertEquals("must not be null", vEmp.getMessage());
        assertEquals("date", vEmp.getPropertyPath().toString());
    }

    @Test
    void b6010331_testExpenseTypeNotBeNull() {
        System.out.println(
                "========== b6010331_testExpenseTypeNotBeNull ==========");

// ---- SAVE Student --- 
        RecordExpense newRecordStu =  new RecordExpense();

        ExpenseType expenseTypeStu = expenseTypeRepository.findById(1);
        newRecordStu.setExpenseType(null);

        newRecordStu.setRec(null);  

        EnrollCourse enrollCourse = enrollCourseRepository.findById(1);
        newRecordStu.setEnrollCourse(enrollCourse);
 
        newRecordStu.setBudget(enrollCourse.getCourse().getPrice());
        
        DateTimeFormatter dateFormatStu = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateStu = LocalDateTime.parse((String)"2019-08-12 10:12:56",dateFormatStu);
        newRecordStu.setDate(dateStu);
        
        Employee createdByStu = employeeRepository.findById(6);
        newRecordStu.setCreatedBy(createdByStu);

// ---- SAVE Employee --- 
        RecordExpense newRecordEmp =  new RecordExpense();

        ExpenseType expenseTypeEmp = expenseTypeRepository.findById(2);
        newRecordEmp.setExpenseType(null);

        Employee rec = employeeRepository.findById(5);
        newRecordEmp.setRec(rec);  

        newRecordEmp.setEnrollCourse(null);
 
        newRecordEmp.setBudget(rec.getPosition().getSalary());
        
        DateTimeFormatter dateFormatEmp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateEmp = LocalDateTime.parse((String)"2019-08-12 10:12:56",dateFormatEmp);
        newRecordEmp.setDate(dateEmp);
        
        Employee createdByEmp = employeeRepository.findById(6);
        newRecordEmp.setCreatedBy(createdByEmp);
    
        Set<ConstraintViolation<RecordExpense>> resultStu = validator.validate(newRecordStu);
        assertEquals(1, resultStu.size());
        ConstraintViolation<RecordExpense> vStu = resultStu.iterator().next();
        assertEquals("must not be null", vStu.getMessage());
        assertEquals("expenseType", vStu.getPropertyPath().toString());

        Set<ConstraintViolation<RecordExpense>> resultEmp = validator.validate(newRecordEmp);
        assertEquals(1, resultEmp.size());
        ConstraintViolation<RecordExpense> vEmp = resultEmp.iterator().next();
        assertEquals("must not be null", vEmp.getMessage());
        assertEquals("expenseType", vEmp.getPropertyPath().toString());
    }

    @Test
    void b6010331_testBudgetMustBeLessThanOrEqualTo50k() {
        System.out.println(
                "========== b6010331_testBudgetMustBeLessThanOrEqualTo50k ==========");

// ---- SAVE Student --- 
        RecordExpense newRecordStu =  new RecordExpense();

        ExpenseType expenseTypeStu = expenseTypeRepository.findById(1);
        newRecordStu.setExpenseType(expenseTypeStu);

        newRecordStu.setRec(null);  

        EnrollCourse enrollCourse = enrollCourseRepository.findById(1);
        newRecordStu.setEnrollCourse(enrollCourse);
 
        newRecordStu.setBudget(50001);
        
        DateTimeFormatter dateFormatStu = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateStu = LocalDateTime.parse((String)"2019-08-12 10:12:56",dateFormatStu);
        newRecordStu.setDate(dateStu);
        
        Employee createdByStu = employeeRepository.findById(6);
        newRecordStu.setCreatedBy(createdByStu);

       

// ---- SAVE Employee --- 
        RecordExpense newRecordEmp =  new RecordExpense();

        ExpenseType expenseTypeEmp = expenseTypeRepository.findById(2);
        newRecordEmp.setExpenseType(expenseTypeEmp);

        Employee rec = employeeRepository.findById(5);
        newRecordEmp.setRec(rec);  

        newRecordEmp.setEnrollCourse(null);
 
        newRecordEmp.setBudget(50001);
        
        DateTimeFormatter dateFormatEmp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateEmp = LocalDateTime.parse((String)"2019-08-12 10:12:56",dateFormatEmp);
        newRecordEmp.setDate(dateEmp);
        
        Employee createdByEmp = employeeRepository.findById(6);
        newRecordEmp.setCreatedBy(createdByEmp);
    
        Set<ConstraintViolation<RecordExpense>> resultStu = validator.validate(newRecordStu);
        assertEquals(1, resultStu.size());
        ConstraintViolation<RecordExpense> vStu = resultStu.iterator().next();
        assertEquals("must be less than or equal to 50000", vStu.getMessage());
        assertEquals("budget", vStu.getPropertyPath().toString());

        Set<ConstraintViolation<RecordExpense>> resultEmp = validator.validate(newRecordEmp);
        assertEquals(1, resultEmp.size());
        ConstraintViolation<RecordExpense> vEmp = resultEmp.iterator().next();
        assertEquals("must be less than or equal to 50000", vEmp.getMessage());
        assertEquals("budget", vEmp.getPropertyPath().toString());
    }

    @Test
    void b6010331_testBudgetMustBeGreaterThanZero() {
        System.out.println("========== b6010331_testBudgetMustBeGreaterThanZero ==========");

// ---- SAVE Student --- 
        RecordExpense newRecordStu =  new RecordExpense();

        ExpenseType expenseTypeStu = expenseTypeRepository.findById(1);
        newRecordStu.setExpenseType(expenseTypeStu);

        newRecordStu.setRec(null);  

        EnrollCourse enrollCourse = enrollCourseRepository.findById(1);
        newRecordStu.setEnrollCourse(enrollCourse);
 
        newRecordStu.setBudget(0);
        
        DateTimeFormatter dateFormatStu = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateStu = LocalDateTime.parse((String)"2019-08-12 10:12:56",dateFormatStu);
        newRecordStu.setDate(dateStu);
        
        Employee createdByStu = employeeRepository.findById(6);
        newRecordStu.setCreatedBy(createdByStu);

       

// ---- SAVE Employee --- 
        RecordExpense newRecordEmp =  new RecordExpense();

        ExpenseType expenseTypeEmp = expenseTypeRepository.findById(2);
        newRecordEmp.setExpenseType(expenseTypeEmp);

        Employee rec = employeeRepository.findById(5);
        newRecordEmp.setRec(rec);  

        newRecordEmp.setEnrollCourse(null);
 
        newRecordEmp.setBudget(0);
        
        DateTimeFormatter dateFormatEmp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateEmp = LocalDateTime.parse((String)"2019-08-12 10:12:56",dateFormatEmp);
        newRecordEmp.setDate(dateEmp);
        
        Employee createdByEmp = employeeRepository.findById(6);
        newRecordEmp.setCreatedBy(createdByEmp);
    
        Set<ConstraintViolation<RecordExpense>> resultStu = validator.validate(newRecordStu);
        assertEquals(1, resultStu.size());
        ConstraintViolation<RecordExpense> vStu = resultStu.iterator().next();
        assertEquals("must be greater than 0", vStu.getMessage());
        assertEquals("budget", vStu.getPropertyPath().toString());

        Set<ConstraintViolation<RecordExpense>> resultEmp = validator.validate(newRecordEmp);
        assertEquals(1, resultEmp.size());
        ConstraintViolation<RecordExpense> vEmp = resultEmp.iterator().next();
        assertEquals("must be greater than 0", vEmp.getMessage());
        assertEquals("budget", vEmp.getPropertyPath().toString());
    }

}