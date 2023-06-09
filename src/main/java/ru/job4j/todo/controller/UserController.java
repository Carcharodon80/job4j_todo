package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("/users/")
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/tasks/all";
    }

    @GetMapping("/registration")
    public String login() {
        return "user/registration";
    }

    //todo добавить вход, выход, аутентификацию
}
