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
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;


    @GetMapping("/all")
    public String showAllTasks(Model model) {
        List<Task> taskList = taskService.findAllTasks();
        model.addAttribute("tasks", taskList);
        model.addAttribute("statusTasks", "all");
        return "task/tasks";
    }

    @GetMapping("/done")
    public String showDoneTasks(Model model) {
        List<Task> taskList = taskService.findDoneTasks();
        model.addAttribute("tasks", taskList);
        model.addAttribute("statusTasks", "done");
        return "task/tasks";
    }

    @GetMapping("/undone")
    public String showUndoneTasks(Model model) {
        List<Task> taskList = taskService.findUndoneTasks();
        model.addAttribute("tasks", taskList);
        model.addAttribute("statusTasks", "undone");
        return "task/tasks";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task) {
        taskService.addTask(task);
        return "redirect:all";
    }

    @GetMapping("/task/{taskId}")
    public String showOneTask(Model model, @PathVariable("taskId") int id) {
        String result = "";
        Task task = taskService.findTaskById(id);
        if (task != null) {
            model.addAttribute("task", task);
            result =  "task/task";
        } else {
            model.addAttribute("message", "заданная задача не найдена.");
            result = "errors/error";
        }
        return result;
    }

    @PostMapping("/delete")
    public String deleteTask(@RequestParam int id, Model model) {
        String result = "";
        if (taskService.deleteTask(id)) {
            result = "redirect:all";
        } else {
            model.addAttribute("message", "удаление задачи в данный момент невозможно.");
            result = "errors/error";
        }
        return result;
    }

    @GetMapping("/changeStatus")
    public String changeStatusOfTask(@RequestParam int id, @RequestParam boolean done, Model model) {
        String result = "";
        if (taskService.changeStatusOfTask(id, done)) {
            result =  "redirect:all";
        } else {
            model.addAttribute("message", "смена статуса задачи в данный момент невозможна.");
            result =  "errors/error";
        }
        return result;
    }

    //TODO сделать как в методе выше???
    @PostMapping("/update")
    public String updateTask(@RequestParam int id, @RequestParam String newTitle,
                                    @RequestParam String newDescription, Model model) {
        String result = "";
        if (taskService.updateTask(id, newTitle, newDescription)) {
            model.addAttribute("task", taskService.findTaskById(id));
            result = "task/task";
        } else {
            model.addAttribute("message", "редактирование задачи в данный момент невозможно.");
            result = "errors/error";
        }
        return result;
    }
}
