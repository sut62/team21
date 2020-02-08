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
public class ImprovementTests {
    private Validator validator;

    @Autowired
    private ImprovementRepository improvementRepository;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void b6010768_testImprovementSuccess(){
        System.out.println(
                "=============================== b6010768_testImprovementSuccess ===============================");
        Improvement newImprovement = new Improvement();
        newImprovement.setImprovementType("Teaching");

        newImprovement = improvementRepository.saveAndFlush(newImprovement);

        Optional<Improvement> found = improvementRepository.findById(newImprovement.getId());
        assertEquals("Teaching", found.get().getImprovementType());
    }
    @Test
    void b6010768_testImprovementMustNotBeNull(){
        System.out.println(
                "=============================== b6010768_testImprovementMustNotBeNull ===============================");
        Improvement newImprovement = new Improvement();
        newImprovement.setImprovementType(null);

        Set<ConstraintViolation<Improvement>> result = validator.validate(newImprovement);
        //error ต้องมี 1 ค่าเท่านั้น
        assertEquals(1,result.size());

        ConstraintViolation<Improvement> v = result.iterator().next();
        assertEquals("must not be null",v.getMessage());
        assertEquals("improvementType",v.getPropertyPath().toString());
    }
}