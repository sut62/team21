package se.g21.backend.login.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestBody;

import se.g21.backend.employeesystem.entities.Employee;
import se.g21.backend.studentsystem.entities.Student;

import se.g21.backend.login.repository.*;
// import com.example.demo.model.*;
import java.util.Collection;
// import java.util.Optional;
// import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class LoginController {

    @Autowired
    private final LoginEmpRepository  loginEmpRepository;

    @Autowired
    private final LoginStuRepository  loginStuRepository;


    public LoginController(LoginEmpRepository  loginEmpRepository, LoginStuRepository  loginStuRepository) {
            this.loginEmpRepository = loginEmpRepository;
            this.loginStuRepository = loginStuRepository;
    }

    @GetMapping("/LoginEmployee/{user}/{pass}")
    public Collection<Employee> LoginEmpAccount(@PathVariable("user") String user,@PathVariable("pass") String pass) {
        return loginEmpRepository.findEmpAccount((String)user,(String)pass).size() == 1 ? loginEmpRepository.findEmpAccount((String)user,(String)pass) : null ;
    }
    
    @GetMapping("/LoginStudent/{user}/{pass}")
    public Collection<Student> LoginStuAccount(@PathVariable("user") String user,@PathVariable("pass") String pass) {
        return loginStuRepository.findStuAccount((String)user,(String)pass).size() == 1 ? loginStuRepository.findStuAccount((String)user,(String)pass) : null  ;
    }

}
