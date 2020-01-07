package se.g21.backend.recordexpensesystem.controllers;
import se.g21.backend.recordexpensesystem.entities.*;
import se.g21.backend.recordexpensesystem.repository.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;

import se.g21.backend.employeesystem.entities.Employee;
import se.g21.backend.employeesystem.repository.EmployeeRepository;
import se.g21.backend.enrollcoursesystem.entities.EnrollCourse;
import se.g21.backend.enrollcoursesystem.repository.EnrollCourseRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RecordExpenseController{

    @Autowired
    private final RecordExpenseRepository recordExpenseRepository;
    @Autowired
    private ExpenseTypeRepository expenseTypeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EnrollCourseRepository enrollCourseRepository;

    RecordExpenseController(RecordExpenseRepository recordExpenseRepository,                            
                                ExpenseTypeRepository expenseTypeRepository,
                                EmployeeRepository employeeRepository,
                                EnrollCourseRepository enrollCourseRepository
                                ) {
        this.recordExpenseRepository = recordExpenseRepository;
        this.expenseTypeRepository = expenseTypeRepository;
        this.employeeRepository = employeeRepository;
        this.enrollCourseRepository = enrollCourseRepository;
    }

    @GetMapping("/recordExpense")
    public Collection<RecordExpense> RecordExpense() {
        return recordExpenseRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/recordExpenseStudent/{expenseType_id}/{enrollCourse_id}/{budget}/{createdDate}/{createdBy_id}")
    public RecordExpense SaveRecordExpenseStudent(RecordExpense newRecordExpense,
                                            @PathVariable long expenseType_id,
                                            @PathVariable long enrollCourse_id,
                                            @PathVariable Double budget,
                                            @PathVariable String createdDate,
                                            @PathVariable long createdBy_id
                                            
                                            ){ 
        
        ExpenseType expenseType = expenseTypeRepository.findById(expenseType_id);
        newRecordExpense.setExpenseType(expenseType);

        EnrollCourse enrollCourse = enrollCourseRepository.findById(enrollCourse_id);
        newRecordExpense.setEnrollCourse(enrollCourse);
 
        newRecordExpense.setBudget((Double)budget);
        
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse((String)createdDate,dateFormat);
        newRecordExpense.setDate(date);
        
        Employee createdBy = employeeRepository.findById(createdBy_id);
        newRecordExpense.setCreatedBy(createdBy);
                                                
        return recordExpenseRepository.save(newRecordExpense);
    }

    @PostMapping("/recordExpenseEmployee/{expenseType_id}/{employee_id}//{budget}/{createdDate}/{createdBy_id}")
    public RecordExpense SaveRecordExpenseEmployee(RecordExpense newRecordExpense,
                                            @PathVariable long expenseType_id,
                                            @PathVariable long employee_id,   
                                            @PathVariable Double budget,
                                            @PathVariable String createdDate,
                                            @PathVariable long createdBy_id
                                            
                                            ){
        
        ExpenseType expenseType = expenseTypeRepository.findById(expenseType_id);
        newRecordExpense.setExpenseType(expenseType);
        
        Employee rec = employeeRepository.findById(employee_id);
        newRecordExpense.setRec(rec);   

        newRecordExpense.setBudget((Double)budget);
        
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse((String)createdDate,dateFormat);
        newRecordExpense.setDate(date);
        
        Employee createdBy = employeeRepository.findById(createdBy_id);
        newRecordExpense.setCreatedBy(createdBy);

        return recordExpenseRepository.save(newRecordExpense);
    }
}
