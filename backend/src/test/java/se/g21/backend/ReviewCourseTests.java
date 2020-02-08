package se.g21.backend;

import se.g21.backend.reviewcoursesystem.entities.*;
import se.g21.backend.reviewcoursesystem.repository.*;

import se.g21.backend.enrollcoursesystem.entities.*;
import se.g21.backend.enrollcoursesystem.repository.*;

import se.g21.backend.employeesystem.entities.*;
import se.g21.backend.employeesystem.repository.*;

import se.g21.backend.studentsystem.entities.*;
import se.g21.backend.studentsystem.repository.*;

import se.g21.backend.coursesystem.entities.*;
import se.g21.backend.coursesystem.repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@DataJpaTest
@SpringBootTest
public class ReviewCourseTests {
    private Validator validator;

    @Autowired
    private ReviewCourseRepository reviewCourseRepository;
    @Autowired
    private EnrollCourseRepository enrollCourseRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ImprovementRepository improvementRepository;

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
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    //    ทดสอบใส่ค่าลงตามปกติ
    @Test
    void b6010768_testReviewCourseSuccess(){
        prepareEnrollCourse();
        System.out.println(
                "=============================== b6010768_testReviewCourseSuccess ===============================");

        ReviewCourse newReviewCourse = new ReviewCourse();

        EnrollCourse enrollCourse = enrollCourseRepository.findById(1);
        newReviewCourse.setEnrollCourse(enrollCourse);

        Rating rating = ratingRepository.findById(2);
        newReviewCourse.setRating(rating);

        Improvement improvement = improvementRepository.findById(1);
        newReviewCourse.setImprovement(improvement);

        newReviewCourse.setComment("1234567890");

        String datetime = "2019-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }

        newReviewCourse.setReviewDate(recorddate);

        newReviewCourse = reviewCourseRepository.saveAndFlush(newReviewCourse);

        Optional<ReviewCourse> found = reviewCourseRepository.findById(newReviewCourse.getId());
        assertEquals(enrollCourse.getCourse().getCourseName(), found.get().getEnrollCourse().getCourse().getCourseName());
        assertEquals(rating.getRatingType(), found.get().getRating().getRatingType());
        assertEquals(improvement.getImprovementType(), found.get().getImprovement().getImprovementType());
        assertEquals("1234567890", found.get().getComment());
        assertEquals(recorddate, found.get().getReviewDate());
    }

    @Test
    void b6010768_testCommentSizeMustBetween1to80(){

        prepareEnrollCourse();

        ReviewCourse newReviewCourse = new ReviewCourse();

        EnrollCourse enrollCourse = enrollCourseRepository.findById(1);
        newReviewCourse.setEnrollCourse(enrollCourse);

        Rating rating = ratingRepository.findById(2);
        newReviewCourse.setRating(rating);

        Improvement improvement = improvementRepository.findById(1);
        newReviewCourse.setImprovement(improvement);

        newReviewCourse.setComment("01234567890123456789012345678901234567890123456789" +
                "0123456789012345678901234567890123456789");

        String datetime = "2018-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newReviewCourse.setReviewDate(recorddate);

        Set<ConstraintViolation<ReviewCourse>> result = validator.validate(newReviewCourse);
        //error ต้องมี 1 ค่าเท่านั้น
        assertEquals(1,result.size());

        ConstraintViolation<ReviewCourse> v = result.iterator().next();
        assertEquals("size must be between 0 and 80",v.getMessage());
        assertEquals("comment",v.getPropertyPath().toString());

    }

    @Test
    void b6010768_testDateMustBePast(){
        prepareEnrollCourse();

        ReviewCourse newReviewCourse = new ReviewCourse();

        EnrollCourse enrollCourse = enrollCourseRepository.findById(1);
        newReviewCourse.setEnrollCourse(enrollCourse);

        Rating rating = ratingRepository.findById(2);
        newReviewCourse.setRating(rating);

        Improvement improvement = improvementRepository.findById(1);
        newReviewCourse.setImprovement(improvement);

        newReviewCourse.setComment("0123456789");

        String datetime = "2020-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newReviewCourse.setReviewDate(recorddate);

        Set<ConstraintViolation<ReviewCourse>> result = validator.validate(newReviewCourse);
        //error ต้องมี 1 ค่าเท่านั้น
        assertEquals(1,result.size());

        ConstraintViolation<ReviewCourse> v = result.iterator().next();
        assertEquals("must be a past date",v.getMessage());
        assertEquals("reviewDate",v.getPropertyPath().toString());
    }

    @Test
    void b6010768_testEnrollCourseMustNotBeNull(){
        ReviewCourse newReviewCourse = new ReviewCourse();

        //        EnrollCourse enrollCourse = enrollCourseRepository.findById(1);
        newReviewCourse.setEnrollCourse(null);

        Rating rating = ratingRepository.findById(2);
        newReviewCourse.setRating(rating);

        Improvement improvement = improvementRepository.findById(1);
        newReviewCourse.setImprovement(improvement);

        newReviewCourse.setComment("0123456789");

        String datetime = "2018-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newReviewCourse.setReviewDate(recorddate);

        Set<ConstraintViolation<ReviewCourse>> result = validator.validate(newReviewCourse);
        //error ต้องมี 1 ค่าเท่านั้น
        assertEquals(1,result.size());

        ConstraintViolation<ReviewCourse> v = result.iterator().next();
        assertEquals("must not be null",v.getMessage());
        assertEquals("enrollCourse",v.getPropertyPath().toString());
    }

    @Test
    void b6010768_testRatingMustNotBeNull(){
        prepareEnrollCourse();

        ReviewCourse newReviewCourse = new ReviewCourse();

        EnrollCourse enrollCourse = enrollCourseRepository.findById(1);
        newReviewCourse.setEnrollCourse(enrollCourse);

        //        Rating rating = ratingRepository.findById(2);
        newReviewCourse.setRating(null);

        Improvement improvement = improvementRepository.findById(1);
        newReviewCourse.setImprovement(improvement);

        newReviewCourse.setComment("0123456789");

        String datetime = "2018-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newReviewCourse.setReviewDate(recorddate);

        Set<ConstraintViolation<ReviewCourse>> result = validator.validate(newReviewCourse);
        //error ต้องมี 1 ค่าเท่านั้น
        assertEquals(1,result.size());

        ConstraintViolation<ReviewCourse> v = result.iterator().next();
        assertEquals("must not be null",v.getMessage());
        assertEquals("rating",v.getPropertyPath().toString());
    }

    @Test
    void b6010768_testImprovementMustNotBeNull(){
        prepareEnrollCourse();

        ReviewCourse newReviewCourse = new ReviewCourse();

        EnrollCourse enrollCourse = enrollCourseRepository.findById(1);
        newReviewCourse.setEnrollCourse(enrollCourse);

        Rating rating = ratingRepository.findById(2);
        newReviewCourse.setRating(rating);

        //        Improvement improvement = improvementRepository.findById(1);
        newReviewCourse.setImprovement(null);

        newReviewCourse.setComment("0123456789");

        String datetime = "2018-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newReviewCourse.setReviewDate(recorddate);

        Set<ConstraintViolation<ReviewCourse>> result = validator.validate(newReviewCourse);
        //error ต้องมี 1 ค่าเท่านั้น
        assertEquals(1,result.size());

        ConstraintViolation<ReviewCourse> v = result.iterator().next();
        assertEquals("must not be null",v.getMessage());
        assertEquals("improvement",v.getPropertyPath().toString());
    }

    @Test
    void b6010768_testCommentMustNotBeNull(){
        prepareEnrollCourse();

        ReviewCourse newReviewCourse = new ReviewCourse();

        EnrollCourse enrollCourse = enrollCourseRepository.findById(1);
        newReviewCourse.setEnrollCourse(enrollCourse);

        Rating rating = ratingRepository.findById(2);
        newReviewCourse.setRating(rating);

        Improvement improvement = improvementRepository.findById(1);
        newReviewCourse.setImprovement(improvement);

        newReviewCourse.setComment(null);

        String datetime = "2018-08-12 10:12:56";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date recorddate = new Date();
        try {
            recorddate = formatter.parse((String) datetime);
        } catch (Exception e) {
            System.out.println(e);
        }
        newReviewCourse.setReviewDate(recorddate);

        Set<ConstraintViolation<ReviewCourse>> result = validator.validate(newReviewCourse);
        //error ต้องมี 1 ค่าเท่านั้น
        assertEquals(1,result.size());

        ConstraintViolation<ReviewCourse> v = result.iterator().next();
        assertEquals("must not be null",v.getMessage());
        assertEquals("comment",v.getPropertyPath().toString());
    }


    void prepareEnrollCourse(){
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

    }
}

