package se.g21.backend.StudentManagemenSystem.controller;

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

import se.g21.backend.StudentManagemenSystem.entity.StudentEntity;
import se.g21.backend.employeesystem.entities.Nametitle;
import se.g21.backend.employeesystem.entities.Gender;
import se.g21.backend.employeesystem.entities.Province;

import se.g21.backend.StudentManagemenSystem.repository.StudentRepository;
import se.g21.backend.employeesystem.repository.GenderRepository;
import se.g21.backend.employeesystem.repository.NametitleRepository;
import se.g21.backend.employeesystem.repository.ProvinceRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class StudentController {
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private NametitleRepository nametitleRepository;


    StudentController(StudentRepository studentRepository, NametitleRepository nametitleRepository,
                     GenderRepository genderRepository, ProvinceRepository provinceRepository) {

        this.studentRepository = studentRepository;
        this.nametitleRepository = nametitleRepository;
        this.genderRepository = genderRepository;
        this.provinceRepository = provinceRepository;

    }

    @GetMapping("/student")
    public Collection<StudentEntity> Students() {
        return studentRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/student/{nametitle_id}/{fullname}/{gender_id}/{old}/{province_id}/{address}/{tel}/{email}/{username}/{password}")
    public StudentEntity newStudent(StudentEntity newStudent,
    @PathVariable long gender_id,
    @PathVariable long province_id,
    @PathVariable long nametitle_id,
    @PathVariable long fullname,
    @PathVariable long old,
    @PathVariable long address,
    @PathVariable long tel,
    @PathVariable long email,
    @PathVariable long username,
    @PathVariable long password) {

    Gender     gender    = genderRepository.findById(gender_id);
    Province   province  = provinceRepository.findById(province_id);
    Nametitle  nametitle = nametitleRepository.findById(nametitle_id);

    newStudent.setGender(gender);
    newStudent.setProvince(province);
    newStudent.setNametitle(nametitle);
    newStudent.setFullname(fullname);
    newStudent.setOld(old);
    newStudent.setAddress(address);
    newStudent.setTel(tel);
    newStudent.setEmail(email);
    newStudent.setUsername(username);
    newStudent.setPassword(password);
    newStudent.setRegDate(new Date());
    
    return studentRepository.save(newStudent); 
    
    }
}