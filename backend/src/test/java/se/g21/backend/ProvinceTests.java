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
public class ProvinceTests {

    private Validator validator;

    @Autowired
    private ProvinceRepository provinceRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B6005795_testProvinceInformationIsOK() {
        Province newProvince = new Province();
        newProvince.setProvince("บุรีรัมย์");
        newProvince = provinceRepository.saveAndFlush(newProvince);

        Optional<Province> found = provinceRepository.findById(newProvince.getId());
        assertEquals("บุรีรัมย์", found.get().getProvince());
    }

    @Test
    void B6005795_testNameOfProvinceMustNotBeNull() {
        Province newProvince = new Province();
        newProvince.setProvince(null);

        Set<ConstraintViolation<Province>> result = validator.validate(newProvince);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Province> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("province", v.getPropertyPath().toString());

    }
}