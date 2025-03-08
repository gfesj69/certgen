package br.com.axys.certgen.controller;

import br.com.axys.certgen.model.Course;
import br.com.axys.certgen.repository.CourseRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/courses")
public class CourseViewController {
    private final CourseRepository repository;

    public CourseViewController(CourseRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        log.info("CourseViewController carregado com sucesso!");
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
        return "add-course";
    }

    @GetMapping("/test")
    @ResponseBody
    public String testTemplate() {
        int x = 1;
        return "O controlador está funcionando!";
    }

    // Processa o formulário e salva o curso no banco
    @PostMapping("/add")
    public String addCourse(@ModelAttribute Course course) {
        repository.save(course);
        return "redirect:/courses"; // Redireciona para a listagem após salvar
    }
}