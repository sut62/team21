package se.g21.backend;

import se.g21.backend.reviewcoursesystem.entities.*;
import se.g21.backend.reviewcoursesystem.repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class RatingTests {
    private Validator validator;

    @Autowired
    private RatingRepository ratingRepository;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void b6010768_testRatingSuccess(){
        System.out.println(
                "=============================== b6010768_testRatingSuccess ===============================");
        Rating newRating = new Rating();
        newRating.setRatingType("very good");

        newRating = ratingRepository.saveAndFlush(newRating);

        Optional<Rating> found = ratingRepository.findById(newRating.getId());
        assertEquals("very good", found.get().getRatingType());
    }
    @Test
    void b6010768_testRatingMustNotBeNull(){
        System.out.println(
                "=============================== b6010768_testRatingMustNotBeNull ===============================");
        Rating newRating = new Rating();
        newRating.setRatingType(null);

        Set<ConstraintViolation<Rating>> result = validator.validate(newRating);
        //error ต้องมี 1 ค่าเท่านั้น
        assertEquals(1,result.size());

        ConstraintViolation<Rating> v = result.iterator().next();
        assertEquals("must not be null",v.getMessage());
        assertEquals("ratingType",v.getPropertyPath().toString());
    }
}