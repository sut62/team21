package se.g21.backend.coursesystem.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// import java.text.SimpleDateFormat;
import java.util.Collection;
// import java.util.Date;
import java.util.stream.Collectors;

import se.g21.backend.coursesystem.entities.*;
import se.g21.backend.coursesystem.repository.*;

import se.g21.backend.employeesystem.entities.*;
import se.g21.backend.employeesystem.repository.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CourseController {

    @Autowired
    private final CourseRepository courseRepository;

    @Autowired
    private  SubjectsRepository subjectsRepository;

    @Autowired
    private  RoomRepository roomRepository;

    @Autowired
    private  TimeRepository timeRepository;

    @Autowired
    private  EmployeeRepository employeeRepository;

    public CourseController(
        CourseRepository courseRepository ,
        SubjectsRepository subjectsRepository ,
        RoomRepository roomRepository ,
        TimeRepository timeRepository ,
        EmployeeRepository employeeRepository
                    ) {
        this.courseRepository = courseRepository;
        this.subjectsRepository = subjectsRepository;
        this.roomRepository = roomRepository;
        this.timeRepository = timeRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/course/")
    public Collection<Course> Course() {
        return courseRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/course/{subjects_id}/{room_id}/{time_id}/{employee_id}")
    public Course newCourse(Course newCourse,
    @PathVariable long subjects_id,
    @PathVariable long room_id,
    @PathVariable long time_id,
    @PathVariable long employee_id) {

    Subjects subjects = subjectsRepository.findById((int) subjects_id);
    newCourse.setSubjects(subjects);

    Room room = roomRepository.findById((int) room_id);
    newCourse.setRoom(room);

    Time time = timeRepository.findById((int) time_id);
    newCourse.setTime(time);

    Employee employee = employeeRepository.findById((int) employee_id);
    newCourse.setEmployee(employee);
     
    return courseRepository.save(newCourse); 
    }

}