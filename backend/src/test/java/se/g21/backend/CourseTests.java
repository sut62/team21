package se.g21.backend;

import se.g21.backend.coursesystem.entities.*;
import se.g21.backend.coursesystem.repository.*;

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
public class CourseTests {

    private Validator validator;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private SubjectsRepository subjectsRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6014728_testSaveData() {
        Course newCourse = new Course();
        Subjects subjects = subjectsRepository.findById(1);
        Room room = roomRepository.findById(1);
        Time time = timeRepository.findById(1);
        Employee employee = employeeRepository.findById(1);
        newCourse.setPrice((double)999);
        newCourse.setCourseName("P'keangai");
        newCourse.setSubjects(subjects);
        newCourse.setRoom(room);
        newCourse.setTime(time);
        newCourse.setEmployee(employee);
    

        newCourse = courseRepository.saveAndFlush(newCourse);

        Optional<Course> found = courseRepository.findById(newCourse.getId());
        assertEquals((double)999, found.get().getPrice());
        assertEquals("P'keangai", found.get().getCourseName());
        assertEquals(room, found.get().getRoom());
        assertEquals(time, found.get().getTime());
        assertEquals(employee, found.get().getEmployee());    
        assertEquals(subjects, found.get().getSubjects());   
    }

    @Test
    void b6014728_testSubjectsMustNotBeNull() {
        Course newCourse = new Course();
        Subjects subjects = subjectsRepository.findById(1);
        Room room = roomRepository.findById(1);
        Time time = timeRepository.findById(1);
        Employee employee = employeeRepository.findById(1);
        newCourse.setCourseName("kaikaikai");
        newCourse.setSubjects(null);
        newCourse.setRoom(room);
        newCourse.setTime(time);
        newCourse.setEmployee(employee);
        newCourse.setPrice((double)999);
    
        Set<ConstraintViolation<Course>> result = validator.validate(newCourse);

        assertEquals(1, result.size());
        ConstraintViolation<Course> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("subjects", v.getPropertyPath().toString());
    }

    @Test
    void b6014728_testPriceMustNotBeZero() {
        Course newCourse = new Course();
        Subjects subjects = subjectsRepository.findById(1);
        Room room = roomRepository.findById(1);
        Time time = timeRepository.findById(1);
        Employee employee = employeeRepository.findById(1);
        newCourse.setCourseName("kaikaikai");
        newCourse.setSubjects(subjects);
        newCourse.setRoom(room);
        newCourse.setTime(time);
        newCourse.setEmployee(employee);
        newCourse.setPrice((double)0);
    
        Set<ConstraintViolation<Course>> result = validator.validate(newCourse);

        assertEquals(1, result.size());
        ConstraintViolation<Course> v = result.iterator().next();
        assertEquals("must be greater than 0", v.getMessage());
        assertEquals("price", v.getPropertyPath().toString());
    }

    @Test
    void b6014728_testCourseNameLowerThan30Character() {
        Course newCourse = new Course();
        Subjects subjects = subjectsRepository.findById(1);
        Room room = roomRepository.findById(1);
        Time time = timeRepository.findById(1);
        Employee employee = employeeRepository.findById(1);
        newCourse.setCourseName("kaikaikaikaikaikaikaikaikaikaikaikai");
        newCourse.setSubjects(subjects);
        newCourse.setRoom(room);
        newCourse.setTime(time);
        newCourse.setEmployee(employee);
        newCourse.setPrice((double)100);
    
        Set<ConstraintViolation<Course>> result = validator.validate(newCourse);

        assertEquals(1, result.size());
        ConstraintViolation<Course> v = result.iterator().next();
        assertEquals("size must be between 0 and 30", v.getMessage());
        assertEquals("courseName", v.getPropertyPath().toString());
    }

    @Test
    void b6014728_testEmployeeMustNotBeNull() {
        Course newCourse = new Course();
        Subjects subjects = subjectsRepository.findById(1);
        Room room = roomRepository.findById(1);
        Time time = timeRepository.findById(1);
        Employee employee = employeeRepository.findById(1);
        newCourse.setCourseName("kaikaikai");
        newCourse.setSubjects(subjects);
        newCourse.setRoom(room);
        newCourse.setTime(time);
        newCourse.setEmployee(null);
        newCourse.setPrice((double)999);
    
        Set<ConstraintViolation<Course>> result = validator.validate(newCourse);

        assertEquals(1, result.size());
        ConstraintViolation<Course> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("employee", v.getPropertyPath().toString());
    }

    @Test
    void b6014728_testTimeMustNotBeNull() {
        Course newCourse = new Course();
        Subjects subjects = subjectsRepository.findById(1);
        Room room = roomRepository.findById(1);
        Time time = timeRepository.findById(1);
        Employee employee = employeeRepository.findById(1);
        newCourse.setCourseName("kaikaikai");
        newCourse.setSubjects(subjects);
        newCourse.setRoom(room);
        newCourse.setTime(null);
        newCourse.setEmployee(employee);
        newCourse.setPrice((double)999);
    
        Set<ConstraintViolation<Course>> result = validator.validate(newCourse);

        assertEquals(1, result.size());
        ConstraintViolation<Course> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("time", v.getPropertyPath().toString());
    }

    @Test
    void b6014728_testRoomIsPatternCharacter() {
       
        Room room = new Room();
        room.setRoom("G005");
        Set<ConstraintViolation<Room>> result = validator.validate(room);

        assertEquals(1, result.size());
        ConstraintViolation<Room> v = result.iterator().next();
        assertEquals("must match \"[R][0-9][0-9][0-9]\"", v.getMessage());
        assertEquals("room", v.getPropertyPath().toString());
    }


}


















