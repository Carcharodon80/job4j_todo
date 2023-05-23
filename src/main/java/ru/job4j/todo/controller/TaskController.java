package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/task/{taskId}")
    public String showOneTask(Model model, @PathVariable("taskId") int id) {
        model.addAttribute("task", taskService.findTaskById(id));
        return "task";
    }

    @PostMapping("/deleteTask")
    public String deleteTask(@RequestParam Integer id) {
        taskService.deleteTask(id);
        return "redirect:allTasks";
    }

    @GetMapping("/changeStatusTask")
    public String changeStatusOfTask(@RequestParam Integer id) {
        taskService.changeStatusOfTask(id);
        return "redirect:allTasks";
    }

    @PostMapping("/updateDescription")
    public String updateDescription(@RequestParam int id, @RequestParam String newDescription, Model model) {
        taskService.updateDescription(id, newDescription);
        model.addAttribute("task", taskService.findTaskById(id));
        return "task";
    }
}
