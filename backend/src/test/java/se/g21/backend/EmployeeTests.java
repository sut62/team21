package se.g21.backend;

import se.g21.backend.employeesystem.entities.*;
import se.g21.backend.employeesystem.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class EmployeeTests {

    private Validator validator;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private NametitleRepository nametitleRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private PositionRepository positionRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6005795_testEmployeeInformationIsOK() {
        Employee newEmployee = new Employee();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Position newPosition = positionRepository.findById(4);
        Province newProvince = provinceRepository.findById(16);
        newEmployee.setNametitle(newNametitle);
        newEmployee.setFullname("พงศกร มาประโคน");
        newEmployee.setGender(newGender);
        newEmployee.setPosition(newPosition);
        newEmployee.setProvince(newProvince);
        newEmployee.setAddress("บ้านกรวด");
        newEmployee.setUsername("opopop");
        newEmployee.setPassword("opopop");
        newEmployee.setEmail("gg@sut.ac.th");

        String datetime = "2019-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newEmployee.setRecorddate(recorddate);

        newEmployee = employeeRepository.saveAndFlush(newEmployee);

        Optional<Employee> found = employeeRepository.findById(newEmployee.getId());
        assertEquals(newNametitle, found.get().getNametitle());
        assertEquals(newGender, found.get().getGender());
        assertEquals(newPosition, found.get().getPosition());
        assertEquals(newProvince, found.get().getProvince());
        assertEquals("พงศกร มาประโคน", found.get().getFullname());
        assertEquals("opopop", found.get().getUsername());
        assertEquals("opopop", found.get().getPassword());
        assertEquals("บ้านกรวด", found.get().getAddress());
        assertEquals(recorddate, found.get().getRecorddate());
        assertEquals("gg@sut.ac.th", found.get().getEmail());
    }

    @Test
    void B6005795_testEmployeeNameTitleMustNotBeNull() {
        Employee newEmployee2 = new Employee();
        Gender newGender = genderRepository.findById(1);
        Position newPosition = positionRepository.findById(4);
        Province newProvince = provinceRepository.findById(16);
        newEmployee2.setNametitle(null);
        newEmployee2.setFullname("พงศกร มาประโคน");
        newEmployee2.setGender(newGender);
        newEmployee2.setPosition(newPosition);
        newEmployee2.setProvince(newProvince);
        newEmployee2.setAddress("บ้านกรวด");
        newEmployee2.setUsername("opopopop");
        newEmployee2.setPassword("opopopop");
        newEmployee2.setEmail("gg@sut.ac.th");

        String datetime = "2019-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newEmployee2.setRecorddate(recorddate);

        Set<ConstraintViolation<Employee>> result = validator.validate(newEmployee2);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("nametitle", v.getPropertyPath().toString());

    }

    @Test
    void B6005795_testEmployeeFullnameMustNotBeNull() {
        Employee newEmployee2 = new Employee();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Position newPosition = positionRepository.findById(4);
        Province newProvince = provinceRepository.findById(16);
        newEmployee2.setNametitle(newNametitle);
        newEmployee2.setFullname(null);
        newEmployee2.setGender(newGender);
        newEmployee2.setPosition(newPosition);
        newEmployee2.setProvince(newProvince);
        newEmployee2.setAddress("บ้านกรวด");
        newEmployee2.setUsername("opopopop");
        newEmployee2.setPassword("opopopop");
        newEmployee2.setEmail("gg@sut.ac.th");

        String datetime = "2019-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newEmployee2.setRecorddate(recorddate);

        Set<ConstraintViolation<Employee>> result = validator.validate(newEmployee2);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("fullname", v.getPropertyPath().toString());

    }

    @Test
    void B6005795_testEmployeeGenderMustNotBeNull() {
        Employee newEmployee2 = new Employee();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Position newPosition = positionRepository.findById(4);
        Province newProvince = provinceRepository.findById(16);
        newEmployee2.setNametitle(newNametitle);
        newEmployee2.setFullname("พงศกร มาประโคน");
        newEmployee2.setGender(null);
        newEmployee2.setPosition(newPosition);
        newEmployee2.setProvince(newProvince);
        newEmployee2.setAddress("บ้านกรวด");
        newEmployee2.setUsername("opopopop");
        newEmployee2.setPassword("opopopop");
        newEmployee2.setEmail("gg@sut.ac.th");

        String datetime = "2019-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newEmployee2.setRecorddate(recorddate);

        Set<ConstraintViolation<Employee>> result = validator.validate(newEmployee2);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("gender", v.getPropertyPath().toString());

    }

    @Test
    void B6005795_testEmployeePositionMustNotBeNull() {
        Employee newEmployee2 = new Employee();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Province newProvince = provinceRepository.findById(16);
        newEmployee2.setNametitle(newNametitle);
        newEmployee2.setFullname("พงศกร มาประโคน");
        newEmployee2.setGender(newGender);
        newEmployee2.setPosition(null);
        newEmployee2.setProvince(newProvince);
        newEmployee2.setAddress("บ้านกรวด");
        newEmployee2.setUsername("opopopop");
        newEmployee2.setPassword("opopopop");
        newEmployee2.setEmail("gg@sut.ac.th");

        String datetime = "2019-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newEmployee2.setRecorddate(recorddate);

        Set<ConstraintViolation<Employee>> result = validator.validate(newEmployee2);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("position", v.getPropertyPath().toString());

    }

    @Test
    void B6005795_testEmployeeProvinceMustNotBeNull() {
        Employee newEmployee2 = new Employee();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Position newPosition = positionRepository.findById(4);
        newEmployee2.setNametitle(newNametitle);
        newEmployee2.setFullname("พงศกร มาประโคน");
        newEmployee2.setGender(newGender);
        newEmployee2.setPosition(newPosition);
        newEmployee2.setProvince(null);
        newEmployee2.setAddress("บ้านกรวด");
        newEmployee2.setUsername("opopopop");
        newEmployee2.setPassword("opopopop");
        newEmployee2.setEmail("gg@sut.ac.th");

        String datetime = "2019-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newEmployee2.setRecorddate(recorddate);

        Set<ConstraintViolation<Employee>> result = validator.validate(newEmployee2);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("province", v.getPropertyPath().toString());

    }

    @Test
    void B6005795_testEmployeeAddressMustNotBeNull() {
        Employee newEmployee2 = new Employee();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Position newPosition = positionRepository.findById(4);
        Province newProvince = provinceRepository.findById(16);
        newEmployee2.setNametitle(newNametitle);
        newEmployee2.setFullname("พงศกร มาประโคน");
        newEmployee2.setGender(newGender);
        newEmployee2.setPosition(newPosition);
        newEmployee2.setProvince(newProvince);
        newEmployee2.setAddress(null);
        newEmployee2.setUsername("opopopop");
        newEmployee2.setPassword("opopopop");
        newEmployee2.setEmail("gg@sut.ac.th");

        String datetime = "2019-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newEmployee2.setRecorddate(recorddate);

        Set<ConstraintViolation<Employee>> result = validator.validate(newEmployee2);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("address", v.getPropertyPath().toString());

    }

    @Test
    void B6005795_testEmployeePasswordMustNotBeNull() {
        Employee newEmployee2 = new Employee();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Position newPosition = positionRepository.findById(4);
        Province newProvince = provinceRepository.findById(16);
        newEmployee2.setNametitle(newNametitle);
        newEmployee2.setFullname("พงศกร มาประโคน");
        newEmployee2.setGender(newGender);
        newEmployee2.setPosition(newPosition);
        newEmployee2.setProvince(newProvince);
        newEmployee2.setAddress("บ้านกรวด");
        newEmployee2.setUsername("opopopop");
        newEmployee2.setPassword(null);
        newEmployee2.setEmail("gg@sut.ac.th");

        String datetime = "2019-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newEmployee2.setRecorddate(recorddate);

        Set<ConstraintViolation<Employee>> result = validator.validate(newEmployee2);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("password", v.getPropertyPath().toString());

    }

    @Test
    void B6005795_testEmployeeUsernameMustNotBeNull() {
        Employee newEmployee2 = new Employee();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Position newPosition = positionRepository.findById(4);
        Province newProvince = provinceRepository.findById(16);
        newEmployee2.setNametitle(newNametitle);
        newEmployee2.setFullname("พงศกร มาประโคน");
        newEmployee2.setGender(newGender);
        newEmployee2.setPosition(newPosition);
        newEmployee2.setProvince(newProvince);
        newEmployee2.setAddress("บ้านกรวด");
        newEmployee2.setUsername(null);
        newEmployee2.setPassword("opopopop");
        newEmployee2.setEmail("gg@sut.ac.th");

        String datetime = "2019-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newEmployee2.setRecorddate(recorddate);

        Set<ConstraintViolation<Employee>> result = validator.validate(newEmployee2);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("username", v.getPropertyPath().toString());

    }

    @Test
    void B6005795_testEmployeeRecordDateMustNotBeNull() {
        Employee newEmployee2 = new Employee();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Position newPosition = positionRepository.findById(4);
        Province newProvince = provinceRepository.findById(16);
        newEmployee2.setNametitle(newNametitle);
        newEmployee2.setFullname("พงศกร มาประโคน");
        newEmployee2.setGender(newGender);
        newEmployee2.setPosition(newPosition);
        newEmployee2.setProvince(newProvince);
        newEmployee2.setAddress("บ้านกรวด");
        newEmployee2.setUsername("opopopop");
        newEmployee2.setPassword("opopopop");
        newEmployee2.setEmail("gg@sut.ac.th");
        newEmployee2.setRecorddate(null);

        Set<ConstraintViolation<Employee>> result = validator.validate(newEmployee2);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("recorddate", v.getPropertyPath().toString());

    }

    @Test
    void B6005795_testEmployeeEmailMustNotBeNull() {
        Employee newEmployee2 = new Employee();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Position newPosition = positionRepository.findById(4);
        Province newProvince = provinceRepository.findById(16);
        newEmployee2.setNametitle(newNametitle);
        newEmployee2.setFullname("พงศกร มาประโคน");
        newEmployee2.setGender(newGender);
        newEmployee2.setPosition(newPosition);
        newEmployee2.setProvince(newProvince);
        newEmployee2.setAddress("บ้านกรวด");
        newEmployee2.setUsername("opopopop");
        newEmployee2.setPassword("opopopop");
        newEmployee2.setEmail(null);
        String datetime = "2019-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newEmployee2.setRecorddate(recorddate);

        Set<ConstraintViolation<Employee>> result = validator.validate(newEmployee2);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());

    }

    @Test
    void B6005795_testEmployeeIsAddressLongerThanMaxSize() {
        Employee newEmployee2 = new Employee();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Position newPosition = positionRepository.findById(4);
        Province newProvince = provinceRepository.findById(16);
        newEmployee2.setNametitle(newNametitle);
        newEmployee2.setFullname("พงศกร มาประโคน");
        newEmployee2.setGender(newGender);
        newEmployee2.setPosition(newPosition);
        newEmployee2.setProvince(newProvince);
        newEmployee2.setAddress(
                "บ้านกรวดบ้านกรวดบ้านกรวดบ้านกรวดบ้านกรวดบ้านกรวดบ้านกรวดบ้านกรวดบ้านกรวดบ้านกรวดบ้านกรวดบ้านกรวดบ้านกรวดบ้านกรวด");
        newEmployee2.setUsername("opopopop");
        newEmployee2.setPassword("opopop");
        newEmployee2.setEmail("gg@sut.ac.th");

        String datetime = "2019-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newEmployee2.setRecorddate(recorddate);

        Set<ConstraintViolation<Employee>> result = validator.validate(newEmployee2);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("size must be between 0 and 100", v.getMessage());
        assertEquals("address", v.getPropertyPath().toString());
    }

    @Test
    void B6005795_testEmployeeEmailValidation() {
        Employee newEmployee2 = new Employee();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Position newPosition = positionRepository.findById(4);
        Province newProvince = provinceRepository.findById(16);
        newEmployee2.setNametitle(newNametitle);
        newEmployee2.setFullname("พงศกร มาประโคน");
        newEmployee2.setGender(newGender);
        newEmployee2.setPosition(newPosition);
        newEmployee2.setProvince(newProvince);
        newEmployee2.setAddress("บ้านกรวด");
        newEmployee2.setUsername("opopopop");
        newEmployee2.setPassword("opopop");
        newEmployee2.setEmail("gg");

        String datetime = "2019-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newEmployee2.setRecorddate(recorddate);

        Set<ConstraintViolation<Employee>> result = validator.validate(newEmployee2);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("must be a well-formed email address", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
    }

    @Test
    void B6005795_testEmployeeIsUsernameStringLongerThanMaxSize() {
        Employee newEmployee2 = new Employee();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Position newPosition = positionRepository.findById(4);
        Province newProvince = provinceRepository.findById(16);
        newEmployee2.setNametitle(newNametitle);
        newEmployee2.setFullname("พงศกร มาประโคน");
        newEmployee2.setGender(newGender);
        newEmployee2.setPosition(newPosition);
        newEmployee2.setProvince(newProvince);
        newEmployee2.setAddress("บ้านกรวด");
        newEmployee2.setUsername("opopopopopopopopopopopop");
        newEmployee2.setPassword("opopop");
        newEmployee2.setEmail("gg@sut.ac.th");

        String datetime = "2019-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newEmployee2.setRecorddate(recorddate);

        Set<ConstraintViolation<Employee>> result = validator.validate(newEmployee2);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("size must be between 4 and 20", v.getMessage());
        assertEquals("username", v.getPropertyPath().toString());
    }

    @Test
    void B6005795_testEmployeeIsUsernameStringShorterThanMinSize() {
        Employee newEmployee2 = new Employee();
        Nametitle newNametitle = nametitleRepository.findById(1);
        Gender newGender = genderRepository.findById(1);
        Position newPosition = positionRepository.findById(4);
        Province newProvince = provinceRepository.findById(16);
        newEmployee2.setNametitle(newNametitle);
        newEmployee2.setFullname("พงศกร มาประโคน");
        newEmployee2.setGender(newGender);
        newEmployee2.setPosition(newPosition);
        newEmployee2.setProvince(newProvince);
        newEmployee2.setAddress("บ้านกรวด");
        newEmployee2.setUsername("opo");
        newEmployee2.setPassword("opopop");
        newEmployee2.setEmail("gg@sut.ac.th");

        String datetime = "2019-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newEmployee2.setRecorddate(recorddate);

        Set<ConstraintViolation<Employee>> result = validator.validate(newEmployee2);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Employee> v = result.iterator().next();
        assertEquals("size must be between 4 and 20", v.getMessage());
        assertEquals("username", v.getPropertyPath().toString());
    }

}