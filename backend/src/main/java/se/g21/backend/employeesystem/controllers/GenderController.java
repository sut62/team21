package se.g21.backend.employeesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import se.g21.backend.employeesystem.entities.Gender;
import se.g21.backend.employeesystem.repository.GenderRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class GenderController {

    @Autowired
    private final GenderRepository genderRepository;

    public GenderController(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @GetMapping("/gender")
    public Collection<Gender> Gender() {
        return genderRepository.findAll().stream().collect(Collectors.toList());
    }

}