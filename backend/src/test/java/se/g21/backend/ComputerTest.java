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

import se.g21.backend.enrollcoursesystem.entities.*;
import se.g21.backend.enrollcoursesystem.repository.*;

import se.g21.backend.coursesystem.entities.Room;
import se.g21.backend.coursesystem.repository.RoomRepository;

//@DataJpaTest
@SpringBootTest
public class ComputerTest {

    private Validator validator;

    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private RoomRepository roomRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6015695_testAddComputerSuccess() {
        System.out.println(
                "=============================== b6015695_testAddComputerSuccess ===============================");

        Computer com = new Computer();
        com.setPcNumber("PC81");
        Room room = roomRepository.findById(4);
        com.setRoom(room);
        computerRepository.saveAndFlush(com);

        Optional<Computer> found = computerRepository.findById(com.getId());
        System.out.println("getPcNumber() : PC81 == "+found.get().getPcNumber());
        System.out.println("room.getRoom() : "+room.getRoom()+" == "+found.get().getRoom().getRoom());

        assertEquals("PC81", found.get().getPcNumber());
        assertEquals(room.getRoom(), found.get().getRoom().getRoom());

    }

    @Test
    void b6015695_testPcNumberPatternCharacterCP() {
        System.out.println(
                "=============================== b6015695_testPcNumberPatternCharacterCP ===============================");
        Computer com = new Computer();
        com.setPcNumber("CP81");
        Room room = roomRepository.findById(4);
        com.setRoom(room);

        Set<ConstraintViolation<Computer>> result = validator.validate(com);

        assertEquals(1, result.size());

        ConstraintViolation<Computer> v = result.iterator().next();

        System.out.println("v.getMessage() == "+v.getMessage());
        System.out.println("v.getPropertyPath().toString() == "+v.getPropertyPath().toString());
        assertEquals("must match \"[P][C][0-9][0-9]\"",v.getMessage());
        assertEquals("pcNumber",v.getPropertyPath().toString());
    }

    @Test
    void b6015695_testPcNumberPatternCharacterPE() {
        System.out.println(
                "=============================== b6015695_testPcNumberPatternCharacterPE ===============================");
        Computer com = new Computer();
        com.setPcNumber("PE81");
        Room room = roomRepository.findById(4);
        com.setRoom(room);

        Set<ConstraintViolation<Computer>> result = validator.validate(com);

        assertEquals(1, result.size());

        ConstraintViolation<Computer> v = result.iterator().next();

        System.out.println("v.getMessage() == "+v.getMessage());
        System.out.println("v.getPropertyPath().toString() == "+v.getPropertyPath().toString());
        assertEquals("must match \"[P][C][0-9][0-9]\"",v.getMessage());
        assertEquals("pcNumber",v.getPropertyPath().toString());
    }

    

    @Test
    void b6015695_testPcNumberMustNotBeNull() {
        System.out.println(
                "=============================== b6015695_testPcNumberMustNotBeNull ===============================");
        Computer com = new Computer();
        com.setPcNumber(null);
        Room room = roomRepository.findById(4);
        com.setRoom(room);

        Set<ConstraintViolation<Computer>> result = validator.validate(com);

        assertEquals(1, result.size());
        System.out.println("result.size() == "+result.size());

        ConstraintViolation<Computer> v = result.iterator().next();

        System.out.println("v.getMessage() == "+v.getMessage());
        System.out.println("v.getPropertyPath().toString() == "+v.getPropertyPath().toString());
        assertEquals("must not be null", v.getMessage());
        assertEquals("pcNumber",v.getPropertyPath().toString());
    }

    @Test
    void b6015695_testRoomMustNotBeNull() {
        System.out.println(
                "=============================== b6015695_testRoomMustNotBeNull ===============================");
        Computer com = new Computer();
        com.setPcNumber("PC81");
        Room room = roomRepository.findById(4);
        com.setRoom(null);

        Set<ConstraintViolation<Computer>> result = validator.validate(com);

        assertEquals(1, result.size());
        System.out.println("result.size() == "+result.size());

        ConstraintViolation<Computer> v = result.iterator().next();

        System.out.println("v.getMessage() == "+v.getMessage());
        System.out.println("v.getPropertyPath().toString() == "+v.getPropertyPath().toString());
        assertEquals("must not be null", v.getMessage());
        assertEquals("room",v.getPropertyPath().toString());
    }

}
