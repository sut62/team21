package se.g21.backend.employeesystem.controllers;

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
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import se.g21.backend.employeesystem.entities.*;
import se.g21.backend.employeesystem.repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class EmployeeController {
    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private NametitleRepository nametitleRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private ProvinceRepository provinceRepository;

    EmployeeController(EmployeeRepository employeeRepository, NametitleRepository nametitleRepository,
            GenderRepository genderRepository, PositionRepository positionRepository,
            ProvinceRepository provinceRepository) {

        this.employeeRepository = employeeRepository;
        this.nametitleRepository = nametitleRepository;
        this.genderRepository = genderRepository;
        this.positionRepository = positionRepository;
        this.provinceRepository = provinceRepository;
    }

    @GetMapping("/employee")
    public Collection<Employee> Employees() {
        return employeeRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/employee/{nametitle_id}/{fullname}/{gender_id}/{position_id}/{province_id}/{address}/{username}/{password}/{datetime}")
    public Employee newEmployee(Employee newEmployee, @PathVariable long nametitle_id, @PathVariable long gender_id,
            @PathVariable long position_id, @PathVariable String fullname, @PathVariable String username,
            @PathVariable String password, @PathVariable long province_id, @PathVariable String address,
            @PathVariable String datetime) {

        Nametitle nametitle = nametitleRepository.findById(nametitle_id);
        Gender gender = genderRepository.findById(gender_id);
        Position position = positionRepository.findById(position_id);
        Province province = provinceRepository.findById(province_id);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }

        newEmployee.setNametitle(nametitle);
        newEmployee.setFullname(fullname);
        newEmployee.setGender(gender);
        newEmployee.setPosition(position);
        newEmployee.setProvince(province);
        newEmployee.setAddress(address);
        newEmployee.setUsername(username);
        newEmployee.setPassword(password);
        newEmployee.setRecorddate(recorddate);

        return employeeRepository.save(newEmployee);

    }
}