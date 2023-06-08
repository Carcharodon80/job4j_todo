package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.todo.model.User;

@Controller
@AllArgsConstructor
public class UserController {
    @PostMapping("/user/addUser")
    //Todo добавить ссылку регистрация, при нажатии идет страница регистрации, реализовать добавление пользователя
    //todo почитать про bootstrap navbar
    public String addUser(@ModelAttribute User user) {
        return "redirect:/task/tasks";
    }

    @GetMapping("/user/addUser")
    public String login() {
        return "redirect:/user/addUser";
    }
}
