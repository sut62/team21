package se.g21.backend.coursesystem.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// import java.text.SimpleDateFormat;
import java.util.Collection;
// import java.util.Date;
import java.util.stream.Collectors;

import se.g21.backend.coursesystem.entities.*;
import se.g21.backend.coursesystem.repository.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TimeController {

    @Autowired
    private final TimeRepository timeRepository;

    public TimeController(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @GetMapping("/time/")
    public Collection<Time> Time() {
        return timeRepository.findAll().stream().collect(Collectors.toList());
    }

}