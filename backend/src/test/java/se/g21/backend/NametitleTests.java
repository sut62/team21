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
public class NametitleTests {

    private Validator validator;

    @Autowired
    private NametitleRepository nametitleRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6005795_testNametitleInformationIsOK() {
        Nametitle newNametitle = new Nametitle();
        newNametitle.setNametitle("นาย");
        newNametitle = nametitleRepository.saveAndFlush(newNametitle);

        Optional<Nametitle> found = nametitleRepository.findById(newNametitle.getId());
        assertEquals("นาย", found.get().getNametitle());
    }

    @Test
    void B6005795_testNameOfNametitleMustNotBeNull() {
        Nametitle newNametitle = new Nametitle();
        newNametitle.setNametitle(null);

        Set<ConstraintViolation<Nametitle>> result = validator.validate(newNametitle);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Nametitle> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("nametitle", v.getPropertyPath().toString());

    }
}