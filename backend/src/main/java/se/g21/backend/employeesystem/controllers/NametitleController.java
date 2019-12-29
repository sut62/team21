package se.g21.backend.employeesystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import se.g21.backend.employeesystem.entities.Nametitle;
import se.g21.backend.employeesystem.repository.NametitleRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class NametitleController {

    @Autowired
    private final NametitleRepository nametitleRepository;

    public NametitleController(NametitleRepository nametitleRepository) {
        this.nametitleRepository = nametitleRepository;
    }

    @GetMapping("/nametitle")
    public Collection<Nametitle> Nametitles() {
        return nametitleRepository.findAll().stream().collect(Collectors.toList());
    }

}