package br.com.axys.certgen.controller;

import br.com.axys.certgen.model.Course;
import br.com.axys.certgen.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseRepository repository;

    public CourseController(CourseRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return repository.save(course);
    }
}