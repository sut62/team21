package se.g21.backend.reviewcoursesystem.controllers;
import se.g21.backend.reviewcoursesystem.entities.Rating;
import se.g21.backend.reviewcoursesystem.repository.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RatingController {
    @Autowired
    private final RatingRepository ratingRepository;

    public RatingController(RatingRepository ratingRepository) { 
        this.ratingRepository = ratingRepository;
    }

    @GetMapping("/Rating")
    public Collection<Rating> Rating(){
        return ratingRepository.findAll().stream().collect(Collectors.toList());
    }
}