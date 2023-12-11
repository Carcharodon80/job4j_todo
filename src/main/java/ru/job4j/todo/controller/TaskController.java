package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.PriorityService;
import ru.job4j.todo.service.TaskService;
import ru.job4j.todo.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;
    private final PriorityService priorityService;

    @GetMapping("/all")
    public String showAllTasks(Model model) {
        List<Task> taskList = taskService.findAllTasks();
        model.addAttribute("tasks", taskList);
        model.addAttribute("priorities", priorityService.findAllPriorities());
        model.addAttribute("statusTasks", "all");
        return "task/tasks";
    }

    @GetMapping("/done")
    public String showDoneTasks(Model model) {
        List<Task> taskList = taskService.findDoneTasks();
        model.addAttribute("tasks", taskList);
        model.addAttribute("priorities", priorityService.findAllPriorities());
        model.addAttribute("statusTasks", "done");
        return "task/tasks";
    }

    @GetMapping("/undone")
    public String showUndoneTasks(Model model) {
        List<Task> taskList = taskService.findUndoneTasks();
        model.addAttribute("tasks", taskList);
        model.addAttribute("priorities", priorityService.findAllPriorities());
        model.addAttribute("statusTasks", "undone");
        return "task/tasks";
    }

    @PostMapping("/add")
    public String addTask(Model model, @ModelAttribute Task task, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            task.setUser(user);
            Priority priority = priorityService.findPriorityById(task.getPriority().getId());
            task.setPriority(priority);
            taskService.addTask(task);
            return "redirect:all";
        } catch (Exception e) {
            model.addAttribute("message", "добавление задачи в данный момент невозможно");
            return "errors/error";
        }
    }

    @GetMapping("/task/{taskId}")
    public String showOneTask(Model model, @PathVariable("taskId") int id) {
        Task task = taskService.findTaskById(id);
        if (task != null) {
            model.addAttribute("task", task);
            model.addAttribute("priorities", priorityService.findAllPriorities());
            return "task/task";
        }
        model.addAttribute("message", "заданная задача не найдена.");
        return "errors/error";
    }

    @PostMapping("/delete")
    public String deleteTask(@RequestParam int id, Model model) {
        if (taskService.deleteTask(id)) {
            return "redirect:all";
        }
        model.addAttribute("message", "удаление задачи в данный момент невозможно.");
        return "errors/error";
    }

    @GetMapping("/changeStatus")
    public String changeStatusOfTask(@RequestParam int id, @RequestParam boolean done, Model model) {
        if (taskService.changeStatusOfTask(id, done)) {
            return "redirect:all";
        }
        model.addAttribute("message", "смена статуса задачи в данный момент невозможна.");
        return "errors/error";
    }

    @PostMapping("/update")
    public String updateTask(@RequestParam int id, @RequestParam String newTitle,
                             @RequestParam String newDescription, Model model) {
        if (taskService.updateTask(id, newTitle, newDescription)) {
            model.addAttribute("task", taskService.findTaskById(id));
            return "task/task";
        }
        model.addAttribute("message", "редактирование задачи в данный момент невозможно.");
        return "errors/error";
    }
}