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

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class GenderTests {

    private Validator validator;

    @Autowired
    private GenderRepository genderRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6005795_testGenderInformationIsOK() {
        Gender newGender = new Gender();
        newGender.setGender("ชาย");

        newGender = genderRepository.saveAndFlush(newGender);

        Optional<Gender> found = genderRepository.findById(newGender.getId());
        assertEquals("ชาย", found.get().getGender());
    }

    @Test
    void B6005795_testNameOfGenderMustNotBeNull() {
        Gender newGender = new Gender();
        newGender.setGender(null);

        Set<ConstraintViolation<Gender>> result = validator.validate(newGender);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Gender> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("gender", v.getPropertyPath().toString());

    }
}