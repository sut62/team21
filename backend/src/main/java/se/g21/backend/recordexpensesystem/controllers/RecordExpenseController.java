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

//import se.g21.backend.employeesystem.entities.Employee;
//import se.g21.backend.employeesystem.repository.EmployeeRepository;
//import se.g21.backend.EnrollCourseSystem.entities.EnrollCourse;
//import se.g21.backend.EnrollCourseSystem.repository.EnrollCourseRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RecordExpenseController{

    @Autowired
    private final RecordExpenseRepository recordExpenseRepository;
    @Autowired
    private ExpenseTypeRepository expenseTypeRepository;
    // @Autowired
    // private employeeRepository employeeRepository;
    // @Autowired
    // private EnrollCourseRepository enrollCourseRepository;

    RecordExpenseController(RecordExpenseRepository recordExpenseRepository,
                                //employeeRepository employeeRepository,
                                ExpenseTypeRepository expenseTypeRepository
                                //EnrollCourseRepository enrollCourseRepository
                                ) {
        this.recordExpenseRepository = recordExpenseRepository;
        //this.employeeRepository = employeeRepository;
        this.expenseTypeRepository = expenseTypeRepository;
        //this.enrollCourseRepository = enrollCourseRepository;
    }

    @GetMapping("/recordExpense")
    public Collection<RecordExpense> RecordExpense() {
        return recordExpenseRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/recordExpense/{budget}/{expenseType_id}")
    public RecordExpense SaveRecordExpense(RecordExpense newRecordExpense,
                                            @PathVariable Double budget,
                                            //@PathVariable long employee_id,
                                            @PathVariable long expenseType_id
                                           // @PathVariable long enrollCourse_id
                                            ){

        //Employee employee = employeeRepository.findById(employee_id);
        ExpenseType expenseType = expenseTypeRepository.findById(expenseType_id);
        //EnrollCourse enrollCourse = enrollCourseRepository.findById(enrollCourse_id);

        newRecordExpense.setBudget((Double)budget);
        //newRecordExpense.setEmployee(employee);
        newRecordExpense.setExpenseType(expenseType);
        //newRecordExpense.setEnrollCourse(enrollCourse);

        return recordExpenseRepository.save(newRecordExpense);
    }
}
