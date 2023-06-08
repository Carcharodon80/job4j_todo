package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.todo.model.User;

@Controller
@AllArgsConstructor
public class UserController {
    @PostMapping("/addUser")
    //Todo сделать контроллер и сервис для добавления user
    public String addUser(@ModelAttribute User user) {
        return null;
    }
}
