package com.example.zadanie25_1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        taskRepository.save(new Task(1L, "hejka", "naklejka", LocalDateTime.of(2023, 1, 1, 12, 00), LocalDateTime.of(2023, 1, 1, 13, 00), Category.HOBBY));
        taskRepository.save(new Task(2L, "cos", "tam", LocalDateTime.of(2023, 1, 1, 16, 30), LocalDateTime.of(2023, 1, 1, 18, 30), Category.HOME_DUTIES));
    }

    @GetMapping("/")
    String home(Model model) {
        List<Task> allTasks = taskRepository.findAllByOrderByCompletionTimeDesc();
        model.addAttribute("allTasks", allTasks);
        return "index";
    }

    @GetMapping("/add")
    String addFormTask(Model model) {
        model.addAttribute("task", new Task());
        return "addTask";
    }

    @GetMapping("/edit/{id}")
    String editFormTask(Model model, @PathVariable Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        model.addAttribute("task", task);
        return "editTask";
    }

    @GetMapping("/completion_time_is_null")
    String allUnfinishedTasks(Model model) {
        List<Task> unfinishedTasks = taskRepository.findTasksByCompletionTimeIsNull();
        model.addAttribute("unfinishedTasks", unfinishedTasks);
        return "unfinishedTasks";
    }

    @GetMapping("/completion_time_is_not_null")
    String allFinishedTasks(Model model) {
        List<Task> finishedTasks = taskRepository.findTasksByCompletionTimeIsNotNull();
        model.addAttribute("finishedTasks", finishedTasks);
        return "finishedTasks";
    }

    @PostMapping("/edit")
    String editTask(Task task) {
        Task updatedTask = taskRepository.findById(task.getId()).orElseThrow();
        updatedTask.setTitle(task.getTitle());
        updatedTask.setDescription(task.getDescription());
        updatedTask.setStartTime(task.getStartTime());
        updatedTask.setCompletionTime(task.getCompletionTime());
        updatedTask.setCategory(task.getCategory());
        taskRepository.save(updatedTask);
        return "redirect:/";
    }

    @PostMapping("/add")
    String addTask(Task task) {
        taskRepository.save(task);
        return "redirect:/";
    }
}
