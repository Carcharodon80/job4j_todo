package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.TaskService;

import java.util.List;

@Controller
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/allTasks")
    public String showAllTasks(Model model) {
        List<Task> taskList = taskService.findAllTasks();
        model.addAttribute("tasks", taskList);
        return "allTasks";
    }

    @GetMapping("/doneTasks")
    public String showDoneTasks(Model model) {
        List<Task> taskList = taskService.findDoneTasks();
        model.addAttribute("tasks", taskList);
        return "doneTasks";
    }

    @GetMapping("/undoneTasks")
    public String showUndoneTasks(Model model) {
        List<Task> taskList = taskService.findUndoneTasks();
        model.addAttribute("tasks", taskList);
        return "undoneTasks";
    }

    @PostMapping("/addTask")
    public String addTask(@ModelAttribute Task task) {
        taskService.addTask(task);
        return "redirect:allTasks";
    }


}
