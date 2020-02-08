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
public class PositionTests {

    private Validator validator;

    @Autowired
    private PositionRepository positionRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6005795_testPositionInformationIsOK() {
        Position newPosition = new Position();
        newPosition.setPosition("ผู้จัดการ");
        newPosition.setSalary(19999.99);

        newPosition = positionRepository.saveAndFlush(newPosition);

        Optional<Position> found = positionRepository.findById(newPosition.getId());
        assertEquals("ผู้จัดการ", found.get().getPosition());
    }

    @Test
    void B6005795_testNameOfPositionMustNotBeNull() {
        Position newPosition = new Position();
        newPosition.setPosition(null);
        newPosition.setSalary(19999.99);

        Set<ConstraintViolation<Position>> result = validator.validate(newPosition);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Position> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("position", v.getPropertyPath().toString());
    }

    @Test
    void B6005795_testSalaryOfPositionMustNotBeNull() {
        Position newPosition = new Position();
        newPosition.setPosition("ผู้จัดการ");
        newPosition.setSalary(null);

        Set<ConstraintViolation<Position>> result = validator.validate(newPosition);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Position> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("salary", v.getPropertyPath().toString());
    }

    @Test
    void B6005795_testSalaryOfPositionGreaterThanZero() {
        Position newPosition = new Position();
        newPosition.setPosition("ผู้จัดการ");
        newPosition.setSalary(-100.00);

        Set<ConstraintViolation<Position>> result = validator.validate(newPosition);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Position> v = result.iterator().next();
        assertEquals("must be greater than or equal to 0", v.getMessage());
        assertEquals("salary", v.getPropertyPath().toString());
    }
}