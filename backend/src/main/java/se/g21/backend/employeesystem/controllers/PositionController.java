package se.g21.backend.employeesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import se.g21.backend.employeesystem.entities.Position;
import se.g21.backend.employeesystem.repository.PositionRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PositionController {

    @Autowired
    private final PositionRepository positionRepository;

    public PositionController(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @GetMapping("/position")
    public Collection<Position> Positions() {
        return positionRepository.findAll().stream().collect(Collectors.toList());
    }

}