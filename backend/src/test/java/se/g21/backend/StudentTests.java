package se.g21.backend;

import se.g21.backend.studentsystem.entities.*;
import se.g21.backend.studentsystem.repository.*;

import se.g21.backend.employeesystem.entities.*;
import se.g21.backend.employeesystem.repository.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class StudentTests {

    private Validator validator;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private NametitleRepository nametitleRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

   @Test
    void B6021511_testStudentInformationIsOK() {
        Student s1 = new Student();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Province newProvince = provinceRepository.findById(1);
        Employee employee = employeeRepository.findById(1);
        s1.setNametitle(newNametitle);
        s1.setFullname("พิชญตม์ อ่ำบุญ");
        s1.setGender(newGender);
        s1.setOld((long)12);   
        s1.setUsername("opopop");
        s1.setPassword("opopop");
        s1.setProvince(newProvince);
        s1.setTel("0999249353");
        s1.setEmail("gg@sut.ac.th");
        s1.setAddress("บ้านกรวด");
        s1.setEmployee(employee);

        s1 = studentRepository.saveAndFlush(s1);

        Optional<Student> found = studentRepository.findById(s1.getId());
        assertEquals(newNametitle, found.get().getNametitle());
        assertEquals(newGender, found.get().getGender());
        assertEquals(newProvince, found.get().getProvince());
        assertEquals("พิชญตม์ อ่ำบุญ", found.get().getFullname());
        assertEquals((long)12, found.get().getOld());
        assertEquals("opopop", found.get().getUsername());
        assertEquals("opopop", found.get().getPassword());
        assertEquals("บ้านกรวด", found.get().getAddress());
        assertEquals("gg@sut.ac.th", found.get().getEmail());
        assertEquals("0999249353", found.get().getTel());
        assertEquals(employee, found.get().getEmployee()); 
    }
    
    @Test
    void B6021511_testStudentNameTitleMustNotBeNull() {
        Student s1 = new Student();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Province newProvince = provinceRepository.findById(1);
        Employee employee = employeeRepository.findById(1);
        s1.setNametitle(null);
        s1.setFullname("พิชญตม์ อ่ำบุญ");
        s1.setGender(newGender);
        s1.setOld((long)12);   
        s1.setUsername("opopop");
        s1.setPassword("opopop");
        s1.setProvince(newProvince);
        s1.setTel("0999249353");
        s1.setEmail("gg@sut.ac.th");
        s1.setAddress("บ้านกรวด");
        s1.setEmployee(employee);


        Set<ConstraintViolation<Student>> result = validator.validate(s1);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Student> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("nametitle", v.getPropertyPath().toString());

    }

    @Test
    void B6021511_testStudentFullnameMustNotBeNull() {
        Student s1 = new Student();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Province newProvince = provinceRepository.findById(1);
        Employee employee = employeeRepository.findById(1);
        s1.setNametitle(newNametitle);
        s1.setFullname(null);
        s1.setGender(newGender);
        s1.setOld((long)12);   
        s1.setUsername("opopop");
        s1.setPassword("opopop");
        s1.setProvince(newProvince);
        s1.setTel("0999249353");
        s1.setEmail("gg@sut.ac.th");
        s1.setAddress("บ้านกรวด");
        s1.setEmployee(employee);

        Set<ConstraintViolation<Student>> result = validator.validate(s1);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Student> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("fullname", v.getPropertyPath().toString());

    }

    @Test
    void B6021511_testStudentGenderMustNotBeNull() {
        Student s1 = new Student();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Province newProvince = provinceRepository.findById(1);
        Employee employee = employeeRepository.findById(1);
        s1.setNametitle(newNametitle);
        s1.setFullname("พิชญตม์ อ่ำบุญ");
        s1.setGender(null);
        s1.setOld((long)12);   
        s1.setUsername("opopop");
        s1.setPassword("opopop");
        s1.setProvince(newProvince);
        s1.setTel("0999249353");
        s1.setEmail("gg@sut.ac.th");
        s1.setAddress("บ้านกรวด");
        s1.setEmployee(employee);

        Set<ConstraintViolation<Student>> result = validator.validate(s1);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Student> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("gender", v.getPropertyPath().toString());

    }


    @Test
    void B6021511_testStudentUsernameMustNotBeNull() {
        Student s1 = new Student();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Province newProvince = provinceRepository.findById(1);
        Employee employee = employeeRepository.findById(1);
        s1.setNametitle(newNametitle);
        s1.setFullname("พิชญตม์ อ่ำบุญ");
        s1.setGender(newGender);
        s1.setOld((long)12);   
        s1.setUsername(null);
        s1.setPassword("opopop");
        s1.setProvince(newProvince);
        s1.setTel("0999249353");
        s1.setEmail("gg@sut.ac.th");
        s1.setAddress("บ้านกรวด");
        s1.setEmployee(employee);

        Set<ConstraintViolation<Student>> result = validator.validate(s1);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Student> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("username", v.getPropertyPath().toString());

    }

    @Test
    void B6021511_testStudentPasswordMustNotBeNull() {
        Student s1 = new Student();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Province newProvince = provinceRepository.findById(1);
        Employee employee = employeeRepository.findById(1);
        s1.setNametitle(newNametitle);
        s1.setFullname("พิชญตม์ อ่ำบุญ");
        s1.setGender(newGender);
        s1.setOld((long)12);   
        s1.setUsername("opopop");
        s1.setPassword(null);
        s1.setProvince(newProvince);
        s1.setTel("0999249353");
        s1.setEmail("gg@sut.ac.th");
        s1.setAddress("บ้านกรวด");
        s1.setEmployee(employee);

        Set<ConstraintViolation<Student>> result = validator.validate(s1);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Student> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("password", v.getPropertyPath().toString());

    }

    @Test
    void B6021511_testStudentProvinceMustNotBeNull() {
        Student s1 = new Student();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Province newProvince = provinceRepository.findById(1);
        Employee employee = employeeRepository.findById(1);
        s1.setNametitle(newNametitle);
        s1.setFullname("พิชญตม์ อ่ำบุญ");
        s1.setGender(newGender);
        s1.setOld((long)12);   
        s1.setUsername("opopop");
        s1.setPassword("opopop");
        s1.setProvince(null);
        s1.setTel("0999249353");
        s1.setEmail("gg@sut.ac.th");
        s1.setAddress("บ้านกรวด");
        s1.setEmployee(employee);

        Set<ConstraintViolation<Student>> result = validator.validate(s1);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Student> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("province", v.getPropertyPath().toString());

    }

    @Test
    void B6021511_testStudentTelephoneMustNotBeNull() {
        Student s1 = new Student();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Province newProvince = provinceRepository.findById(1);
        Employee employee = employeeRepository.findById(1);
        s1.setNametitle(newNametitle);
        s1.setFullname("พิชญตม์ อ่ำบุญ");
        s1.setGender(newGender);
        s1.setOld((long)12);   
        s1.setUsername("opopop");
        s1.setPassword("opopop");
        s1.setProvince(newProvince);
        s1.setTel(null);
        s1.setEmail("gg@sut.ac.th");
        s1.setAddress("บ้านกรวด");
        s1.setEmployee(employee);

        Set<ConstraintViolation<Student>> result = validator.validate(s1);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Student> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());

    }

    @Test
    void B6021511_testEmailMustNotBeNull() {
        Student s1 = new Student();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Province newProvince = provinceRepository.findById(1);
        Employee employee = employeeRepository.findById(1);
        s1.setNametitle(newNametitle);
        s1.setFullname("พิชญตม์ อ่ำบุญ");
        s1.setGender(newGender);
        s1.setOld((long)12);   
        s1.setUsername("opopop");
        s1.setPassword("opopop");
        s1.setProvince(newProvince);
        s1.setTel("0999249353");
        s1.setEmail(null);
        s1.setAddress("บ้านกรวด");
        s1.setEmployee(employee);
    
        Set<ConstraintViolation<Student>> result = validator.validate(s1);

        assertEquals(1, result.size());
        ConstraintViolation<Student> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
    }

    @Test
    void B6021511_testStudentEmailValidation() {
        Student s1 = new Student();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Province newProvince = provinceRepository.findById(1);
        Employee employee = employeeRepository.findById(1);
        s1.setNametitle(newNametitle);
        s1.setFullname("พิชญตม์ อ่ำบุญ");
        s1.setGender(newGender);
        s1.setOld((long)12);   
        s1.setUsername("opopop");
        s1.setPassword("opopop");
        s1.setProvince(newProvince);
        s1.setTel("0999249353");
        s1.setEmail("ez");
        s1.setAddress("บ้านกรวด");
        s1.setEmployee(employee);

        Set<ConstraintViolation<Student>> result = validator.validate(s1);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Student> v = result.iterator().next();
        assertEquals("must be a well-formed email address", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
    }

    @Test
    void B6021511_testStudentOldMustNotBeZero() {
        Student s1 = new Student();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Province newProvince = provinceRepository.findById(1);
        Employee employee = employeeRepository.findById(1);
        s1.setNametitle(newNametitle);
        s1.setFullname("พิชญตม์ อ่ำบุญ");
        s1.setGender(newGender);
        s1.setOld((long)0);   
        s1.setUsername("opopop");
        s1.setPassword("opopop");
        s1.setProvince(newProvince);
        s1.setTel("0999249353");
        s1.setEmail("gg@sut.ac.th");
        s1.setAddress("บ้านกรวด");
        s1.setEmployee(employee);

        Set<ConstraintViolation<Student>> result = validator.validate(s1);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Student> v = result.iterator().next();
        assertEquals("must be greater than 0", v.getMessage());
        assertEquals("old", v.getPropertyPath().toString());

    }
}
    


















