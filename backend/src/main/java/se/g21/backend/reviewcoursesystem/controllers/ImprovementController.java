package se.g21.backend.reviewcoursesystem.controllers;
import se.g21.backend.reviewcoursesystem.entities.Improvement;
import se.g21.backend.reviewcoursesystem.repository.ImprovementRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ImprovementController {
    @Autowired
    private final ImprovementRepository improvementRepository;

    public ImprovementController(ImprovementRepository improvementRepository) { 
        this.improvementRepository = improvementRepository;
    }

    @GetMapping("/improvement")
    public Collection<Improvement> Improvement(){
        return improvementRepository.findAll().stream().collect(Collectors.toList());
    }
}