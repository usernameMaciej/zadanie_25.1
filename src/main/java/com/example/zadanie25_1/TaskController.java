package com.example.zadanie25_1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    String home(Model model) {
        LocalDateTime now = LocalDateTime.now();
        taskRepository.addTask("cos", "tam", now, now);
        taskRepository.addTask("cos", "tam", now, now);
        List<Task> allTasks = taskRepository.findAllByOrderByCompletionTimeDesc();
        model.addAttribute("allTasks", allTasks);
        return "index";
    }

    @GetMapping("/add")
    String addFormTask(Model model) {
        model.addAttribute("task", new Task());
        return "addTask";
    }

    @PostMapping("/add")
    String addTask(Task task) {
        taskRepository.addTask(task.getTitle(), task.getDescription(), task.getStartTime(), task.getCompletionTime());
        return "redirect:/";
    }
}
