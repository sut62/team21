package se.g21.backend.enrollcoursesystem.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import se.g21.backend.enrollcoursesystem.entities.*;
import se.g21.backend.enrollcoursesystem.repository.*;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ViewEnrollCourseController {

    @Autowired
    private ViewEnrollCourseRepository viewEnrollCourseRepository;


    public ViewEnrollCourseController(
        ViewEnrollCourseRepository viewEnrollCourseRepository 
        ) {
        
        this.viewEnrollCourseRepository = viewEnrollCourseRepository;
    }

    @GetMapping("/viewEnrollCourse/{fullname}/")
    public Collection<EnrollCourse> findByStudentName(@PathVariable("fullname") String fullname) {
        // SQL LIKE
        fullname = fullname + "%";
        return viewEnrollCourseRepository.findByStudentName(fullname);
    }

    @GetMapping("/viewEnrollCourse/all/")
    public Collection<EnrollCourse> findAllEnrollCourses() {
        return viewEnrollCourseRepository.findAllEnrollCourses();
    }

}