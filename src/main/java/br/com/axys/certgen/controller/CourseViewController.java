package br.com.axys.certgen.controller;

import br.com.axys.certgen.model.Course;
import br.com.axys.certgen.repository.CourseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses-view")
public class CourseViewController {
    private final CourseRepository repository;

    public CourseViewController(CourseRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listCourses(Model model) {
        List<Course> courses = repository.findAll();
        model.addAttribute("courses", courses);
        return "courses/index"; // O Thymeleaf buscará templates/courses/index.html
    }

    // Página para adicionar um novo curso
    @GetMapping("/add")
    public String showAddCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "courses/add"; // templates/courses/add.html
    }

    // Processa o formulário e salva o curso no banco
    @PostMapping("/add")
    public String addCourse(@ModelAttribute Course course) {
        repository.save(course);
        return "redirect:/courses-view"; // Redireciona para a listagem após salvar
    }
}