package se.g21.backend.enrollcoursesystem.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// import java.text.SimpleDateFormat;
import java.util.Collection;
// import java.util.Date;
import java.util.stream.Collectors;

import se.g21.backend.enrollcoursesystem.repository.*;
import se.g21.backend.enrollcoursesystem.entities.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ComputerController {

    @Autowired
    private final ComputerRepository computerRepository;

    public ComputerController(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    @GetMapping("/computer/")
    public Collection<Computer> Course() {
        return computerRepository.findAll().stream().collect(Collectors.toList());
    }

}