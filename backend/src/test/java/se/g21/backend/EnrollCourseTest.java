package se.g21.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import se.g21.backend.enrollcoursesystem.entities.*;
import se.g21.backend.enrollcoursesystem.repository.*;

import se.g21.backend.employeesystem.entities.*;
import se.g21.backend.employeesystem.repository.*;

import se.g21.backend.studentsystem.entities.*;
import se.g21.backend.studentsystem.repository.*;

import se.g21.backend.coursesystem.entities.*;
import se.g21.backend.coursesystem.repository.*;

//@DataJpaTest
@SpringBootTest
public class EnrollCourseTest {

    private Validator validator;

    @Autowired
    private EnrollCourseRepository enrollCourseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    ComputerRepository computerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    //Course Repository
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private SubjectsRepository subjectsRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6015695_testAddEnrollCourseSuccess() {
        System.out.println(
                "=============================== b6015695_testAddEnrollCourseSuccess ===============================");

        EnrollCourse newEnrollCourse = new EnrollCourse();

        Student student = studentRepository.findById(2);
        newEnrollCourse.setStudent(student);

        //set Course
        Course course = new Course();
        Subjects subjectsCourse = subjectsRepository.findById(1);
        Room roomCourse = roomRepository.findById(1);
        Time timeCourse = timeRepository.findById(1);
        Employee employeeCourse = employeeRepository.findById(1);
        course.setPrice((double)999);
        course.setCourseName("Course A");
        course.setSubjects(subjectsCourse);
        course.setRoom(roomCourse);
        course.setTime(timeCourse);
        course.setEmployee(employeeCourse);
        course = courseRepository.saveAndFlush(course);
        newEnrollCourse.setCourse(course);

        Computer computer = computerRepository.findById(5);
        newEnrollCourse.setComputer(computer);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dataDate = LocalDateTime.parse((String) "2019-08-12 10:12:56", dateFormat);
        newEnrollCourse.setDate(dataDate);

        newEnrollCourse.setNote("-");

        Employee employee = employeeRepository.findById(3);
        newEnrollCourse.setEmployee(employee);

        enrollCourseRepository.saveAndFlush(newEnrollCourse);

        Optional<EnrollCourse> found = enrollCourseRepository.findById(newEnrollCourse.getId());
        System.out.println(
                "student.getFullname() : " + student.getFullname() + " == " + found.get().getStudent().getFullname());
        System.out.println("course.getCourseName() : " + course.getCourseName() + " == "
                + found.get().getCourse().getCourseName());
        System.out.println("computer.getPcNumber() : " + computer.getPcNumber() + " == "
                + found.get().getComputer().getPcNumber());
        System.out.println("dataDate.format(dateFormat) : " + dataDate.format(dateFormat) + " == "
                + found.get().getDate().format(dateFormat));
        System.out.println("note : - == " + found.get().getNote());
        System.out.println("employee.getFullname(), : " + employee.getFullname() + " == "
                + found.get().getEmployee().getFullname());

        assertEquals(student.getFullname(), found.get().getStudent().getFullname());
        assertEquals(course.getCourseName(), found.get().getCourse().getCourseName());
        assertEquals(computer.getPcNumber(), found.get().getComputer().getPcNumber());
        assertEquals(dataDate.format(dateFormat), found.get().getDate().format(dateFormat));
        assertEquals("-", found.get().getNote());
        assertEquals(employee.getFullname(), found.get().getEmployee().getFullname());

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
    void b6015695_testDateForMat() {
        System.out.println("=============================== b6015695_testDateForMat ===============================");

        EnrollCourse newEnrollCourse = new EnrollCourse();

        Student student = studentRepository.findById(2);
        newEnrollCourse.setStudent(student);

        //set Course
        Course course = new Course();
        Subjects subjectsCourse = subjectsRepository.findById(1);
        Room roomCourse = roomRepository.findById(1);
        Time timeCourse = timeRepository.findById(1);
        Employee employeeCourse = employeeRepository.findById(1);
        course.setPrice((double)999);
        course.setCourseName("Course A");
        course.setSubjects(subjectsCourse);
        course.setRoom(roomCourse);
        course.setTime(timeCourse);
        course.setEmployee(employeeCourse);
        course = courseRepository.saveAndFlush(course);
        newEnrollCourse.setCourse(course);

        Computer computer = computerRepository.findById(5);
        newEnrollCourse.setComputer(computer);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dataDate = LocalDateTime.parse((String) "2019-08-12 10:12:56", dateFormat);
        newEnrollCourse.setDate(dataDate);

        newEnrollCourse.setNote("-");

        Employee employee = employeeRepository.findById(3);
        newEnrollCourse.setEmployee(employee);

        enrollCourseRepository.saveAndFlush(newEnrollCourse);

        Optional<EnrollCourse> found = enrollCourseRepository.findById(newEnrollCourse.getId());
        System.out.println("isValidDateForMat = " + isValidDateForMat(found.get().getDate().format(dateFormat)));

        assertEquals(student.getFullname(), found.get().getStudent().getFullname());
        assertEquals(course.getCourseName(), found.get().getCourse().getCourseName());
        assertEquals(computer.getPcNumber(), found.get().getComputer().getPcNumber());
        // assertTrue(isValidDateForMat("2019-08-12"));
        assertTrue(isValidDateForMat(found.get().getDate().format(dateFormat)));
        assertEquals("-", found.get().getNote());
        assertEquals(employee.getFullname(), found.get().getEmployee().getFullname());

    }

    @Test
    void b6015695_testNoteSize51Character() {
        System.out.println(
                "=============================== b6015695_testNoteSize51Character ===============================");
        EnrollCourse newEnrollCourse = new EnrollCourse();

        Student student = studentRepository.findById(2);
        newEnrollCourse.setStudent(student);

        //set Course
        Course course = new Course();
        Subjects subjectsCourse = subjectsRepository.findById(1);
        Room roomCourse = roomRepository.findById(1);
        Time timeCourse = timeRepository.findById(1);
        Employee employeeCourse = employeeRepository.findById(1);
        course.setPrice((double)999);
        course.setCourseName("Course A");
        course.setSubjects(subjectsCourse);
        course.setRoom(roomCourse);
        course.setTime(timeCourse);
        course.setEmployee(employeeCourse);
        course = courseRepository.saveAndFlush(course);
        newEnrollCourse.setCourse(course);

        Computer computer = computerRepository.findById(5);
        newEnrollCourse.setComputer(computer);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dataDate = LocalDateTime.parse((String) "2019-08-12 10:12:56", dateFormat);
        newEnrollCourse.setDate(dataDate);

        newEnrollCourse.setNote(
                "Safety is integrated within Loram’s core values. We are dedicated to establishing and maintaining the industry’s highest standards to protect your personnel, your property and our people. Loram complies with all occupational regulations and industry best practices, together with industry-leading initiatives, training and actionable plans that help ensure safety at all times. A proud recipient of the NRC Contractor of the Year award in safety, Loram makes safety a cornerstone commitment across our business, operations, equipment and trained personnel.");

        Employee employee = employeeRepository.findById(3);
        newEnrollCourse.setEmployee(employee);

        Set<ConstraintViolation<EnrollCourse>> result = validator.validate(newEnrollCourse);

        assertEquals(1, result.size());
        System.out.println("result.size() == " + result.size());

        ConstraintViolation<EnrollCourse> v = result.iterator().next();

        System.out.println("v.getMessage() == " + v.getMessage());
        System.out.println("v.getPropertyPath().toString() == " + v.getPropertyPath().toString());
        assertEquals("size must be between 1 and 50", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }

    @Test
    void b6015695_testNoteSizeZeroCharacter() {
        System.out.println(
                "=============================== b6015695_testNoteSizeZeroCharacter ===============================");
        EnrollCourse newEnrollCourse = new EnrollCourse();

        Student student = studentRepository.findById(2);
        newEnrollCourse.setStudent(student);

        //set Course
        Course course = new Course();
        Subjects subjectsCourse = subjectsRepository.findById(1);
        Room roomCourse = roomRepository.findById(1);
        Time timeCourse = timeRepository.findById(1);
        Employee employeeCourse = employeeRepository.findById(1);
        course.setPrice((double)999);
        course.setCourseName("Course A");
        course.setSubjects(subjectsCourse);
        course.setRoom(roomCourse);
        course.setTime(timeCourse);
        course.setEmployee(employeeCourse);
        course = courseRepository.saveAndFlush(course);
        newEnrollCourse.setCourse(course);

        Computer computer = computerRepository.findById(5);
        newEnrollCourse.setComputer(computer);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dataDate = LocalDateTime.parse((String) "2019-08-12 10:12:56", dateFormat);
        newEnrollCourse.setDate(dataDate);

        newEnrollCourse.setNote("");

        Employee employee = employeeRepository.findById(3);
        newEnrollCourse.setEmployee(employee);

        Set<ConstraintViolation<EnrollCourse>> result = validator.validate(newEnrollCourse);

        assertEquals(1, result.size());
        System.out.println("result.size() == " + result.size());

        ConstraintViolation<EnrollCourse> v = result.iterator().next();

        System.out.println("v.getMessage() == " + v.getMessage());
        System.out.println("v.getPropertyPath().toString() == " + v.getPropertyPath().toString());
        assertEquals("size must be between 1 and 50", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }

    @Test
    void b6015695_testStudentNotBeNull() {
        System.out.println(
                "=============================== b6015695_testStudentNotBeNull ===============================");

        EnrollCourse newEnrollCourse = new EnrollCourse();

        // Student student = studentRepository.findById(2);
        newEnrollCourse.setStudent(null);

        //set Course
        Course course = new Course();
        Subjects subjectsCourse = subjectsRepository.findById(1);
        Room roomCourse = roomRepository.findById(1);
        Time timeCourse = timeRepository.findById(1);
        Employee employeeCourse = employeeRepository.findById(1);
        course.setPrice((double)999);
        course.setCourseName("Course A");
        course.setSubjects(subjectsCourse);
        course.setRoom(roomCourse);
        course.setTime(timeCourse);
        course.setEmployee(employeeCourse);
        course = courseRepository.saveAndFlush(course);
        newEnrollCourse.setCourse(course);

        Computer computer = computerRepository.findById(5);
        newEnrollCourse.setComputer(computer);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dataDate = LocalDateTime.parse((String) "2019-08-12 10:12:56", dateFormat);
        newEnrollCourse.setDate(dataDate);

        newEnrollCourse.setNote("-");

        Employee employee = employeeRepository.findById(3);
        newEnrollCourse.setEmployee(employee);

        Set<ConstraintViolation<EnrollCourse>> result = validator.validate(newEnrollCourse);

        assertEquals(1, result.size());

        ConstraintViolation<EnrollCourse> v = result.iterator().next();
        System.out.println("v.getMessage() = " + v.getMessage());
        System.out.println("getPropertyPath() = " + v.getPropertyPath().toString());
        assertEquals("must not be null", v.getMessage());
        assertEquals("student", v.getPropertyPath().toString());

    }

    @Test
    void b6015695_testCourseMustNotBeNull() {
        System.out.println(
                "=============================== b6015695_testCourseMustNotBeNull ===============================");

        EnrollCourse newEnrollCourse = new EnrollCourse();

        Student student = studentRepository.findById(2);
        newEnrollCourse.setStudent(student);

        newEnrollCourse.setCourse(null);

        Computer computer = computerRepository.findById(5);
        newEnrollCourse.setComputer(computer);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dataDate = LocalDateTime.parse((String) "2019-08-12 10:12:56", dateFormat);
        newEnrollCourse.setDate(dataDate);

        newEnrollCourse.setNote("-");

        Employee employee = employeeRepository.findById(3);
        newEnrollCourse.setEmployee(employee);

        Set<ConstraintViolation<EnrollCourse>> result = validator.validate(newEnrollCourse);

        assertEquals(1, result.size());

        ConstraintViolation<EnrollCourse> v = result.iterator().next();
        System.out.println("v.getMessage() = " + v.getMessage());
        System.out.println("getPropertyPath() = " + v.getPropertyPath().toString());
        assertEquals("must not be null", v.getMessage());
        assertEquals("course", v.getPropertyPath().toString());

    }

    @Test
    void b6015695_testComputerMustNotBeNull() {
        System.out.println(
                "=============================== b6015695_testComputerMustNotBeNull ===============================");

        EnrollCourse newEnrollCourse = new EnrollCourse();

        Student student = studentRepository.findById(2);
        newEnrollCourse.setStudent(student);

        //set Course
        Course course = new Course();
        Subjects subjectsCourse = subjectsRepository.findById(1);
        Room roomCourse = roomRepository.findById(1);
        Time timeCourse = timeRepository.findById(1);
        Employee employeeCourse = employeeRepository.findById(1);
        course.setPrice((double)999);
        course.setCourseName("Course A");
        course.setSubjects(subjectsCourse);
        course.setRoom(roomCourse);
        course.setTime(timeCourse);
        course.setEmployee(employeeCourse);
        course = courseRepository.saveAndFlush(course);
        newEnrollCourse.setCourse(course);

        // Computer computer = computerRepository.findById(5);
        newEnrollCourse.setComputer(null);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dataDate = LocalDateTime.parse((String) "2019-08-12 10:12:56", dateFormat);
        newEnrollCourse.setDate(dataDate);

        newEnrollCourse.setNote("-");

        Employee employee = employeeRepository.findById(3);
        newEnrollCourse.setEmployee(employee);

        Set<ConstraintViolation<EnrollCourse>> result = validator.validate(newEnrollCourse);

        assertEquals(1, result.size());

        ConstraintViolation<EnrollCourse> v = result.iterator().next();
        System.out.println("v.getMessage() = " + v.getMessage());
        System.out.println("getPropertyPath() = " + v.getPropertyPath().toString());
        assertEquals("computer", v.getPropertyPath().toString());

    }

    @Test
    void b6015695_testDateMustNotBeNull() {
        System.out.println(
                "=============================== b6015695_testDateMustNotBeNull ===============================");

        EnrollCourse newEnrollCourse = new EnrollCourse();

        Student student = studentRepository.findById(2);
        newEnrollCourse.setStudent(student);

        //set Course
        Course course = new Course();
        Subjects subjectsCourse = subjectsRepository.findById(1);
        Room roomCourse = roomRepository.findById(1);
        Time timeCourse = timeRepository.findById(1);
        Employee employeeCourse = employeeRepository.findById(1);
        course.setPrice((double)999);
        course.setCourseName("Course A");
        course.setSubjects(subjectsCourse);
        course.setRoom(roomCourse);
        course.setTime(timeCourse);
        course.setEmployee(employeeCourse);
        course = courseRepository.saveAndFlush(course);
        newEnrollCourse.setCourse(course);

        Computer computer = computerRepository.findById(5);
        newEnrollCourse.setComputer(computer);

        newEnrollCourse.setDate(null);

        newEnrollCourse.setNote("-");

        Employee employee = employeeRepository.findById(3);
        newEnrollCourse.setEmployee(employee);

        Set<ConstraintViolation<EnrollCourse>> result = validator.validate(newEnrollCourse);

        assertEquals(1, result.size());

        ConstraintViolation<EnrollCourse> v = result.iterator().next();
        System.out.println("v.getMessage() = " + v.getMessage());
        System.out.println("getPropertyPath() = " + v.getPropertyPath().toString());
        assertEquals("must not be null", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());

    }

    void b6015695_testNoteMustNotBeNull() {
        System.out.println(
                "=============================== b6015695_testDateMustNotBeNull ===============================");

        EnrollCourse newEnrollCourse = new EnrollCourse();

        Student student = studentRepository.findById(2);
        newEnrollCourse.setStudent(student);

        //set Course
        Course course = new Course();
        Subjects subjectsCourse = subjectsRepository.findById(1);
        Room roomCourse = roomRepository.findById(1);
        Time timeCourse = timeRepository.findById(1);
        Employee employeeCourse = employeeRepository.findById(1);
        course.setPrice((double)999);
        course.setCourseName("Course A");
        course.setSubjects(subjectsCourse);
        course.setRoom(roomCourse);
        course.setTime(timeCourse);
        course.setEmployee(employeeCourse);
        course = courseRepository.saveAndFlush(course);
        newEnrollCourse.setCourse(course);

        Computer computer = computerRepository.findById(5);
        newEnrollCourse.setComputer(computer);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dataDate = LocalDateTime.parse((String) "2019-08-12 10:12:56", dateFormat);
        newEnrollCourse.setDate(dataDate);

        newEnrollCourse.setNote(null);

        Employee employee = employeeRepository.findById(3);
        newEnrollCourse.setEmployee(employee);

        Set<ConstraintViolation<EnrollCourse>> result = validator.validate(newEnrollCourse);

        assertEquals(1, result.size());

        ConstraintViolation<EnrollCourse> v = result.iterator().next();
        System.out.println("v.getMessage() = " + v.getMessage());
        System.out.println("getPropertyPath() = " + v.getPropertyPath().toString());
        assertEquals("must not be null", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());

    }

    @Test
    void b6015695_testEmployeeMustNotBeNull() {
        System.out.println(
                "=============================== b6015695_testEmployeeMustNotBeNull ===============================");

        EnrollCourse newEnrollCourse = new EnrollCourse();

        Student student = studentRepository.findById(2);
        newEnrollCourse.setStudent(student);

        //set Course
        Course course = new Course();
        Subjects subjectsCourse = subjectsRepository.findById(1);
        Room roomCourse = roomRepository.findById(1);
        Time timeCourse = timeRepository.findById(1);
        Employee employeeCourse = employeeRepository.findById(1);
        course.setPrice((double)999);
        course.setCourseName("Course A");
        course.setSubjects(subjectsCourse);
        course.setRoom(roomCourse);
        course.setTime(timeCourse);
        course.setEmployee(employeeCourse);
        course = courseRepository.saveAndFlush(course);
        newEnrollCourse.setCourse(course);

        Computer computer = computerRepository.findById(5);
        newEnrollCourse.setComputer(computer);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dataDate = LocalDateTime.parse((String) "2019-08-12 10:12:56", dateFormat);
        newEnrollCourse.setDate(dataDate);

        newEnrollCourse.setNote("-");

        // Employee employee = employeeRepository.findById(3);
        newEnrollCourse.setEmployee(null);

        Set<ConstraintViolation<EnrollCourse>> result = validator.validate(newEnrollCourse);

        assertEquals(1, result.size());

        ConstraintViolation<EnrollCourse> v = result.iterator().next();
        System.out.println("v.getMessage() = " + v.getMessage());
        System.out.println("getPropertyPath() = " + v.getPropertyPath().toString());
        assertEquals("must not be null", v.getMessage());
        assertEquals("employee", v.getPropertyPath().toString());

    }

}
