package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.User;
import ru.job4j.todo.service.UserService;
import ru.job4j.todo.util.AllTimeZones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.TimeZone;

@Controller
@AllArgsConstructor
@RequestMapping("/users/")
public class UserController {
    private final UserService userService;

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("zones", AllTimeZones.findAllTimezones());
        model.addAttribute("defaultZone", TimeZone.getDefault());
        return "user/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user, @RequestParam String zoneID, Model model) {
        user.setTimezone(TimeZone.getTimeZone(zoneID));
        boolean isAddedUser = userService.addUser(user);
        if (!isAddedUser) {
            model.addAttribute("message",
                    "пользователь с таким именем или логином уже существует");
            return "errors/error";
        }
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, HttpServletRequest request) {
        Optional<User> userOptional = userService.findUser(user.getLogin(), user.getPassword());
        if (userOptional.isEmpty()) {
            model.addAttribute("error", "Пользователь с таким логином и паролем не найден.");
            return "user/login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", userOptional.get());
        return "redirect:/tasks/all";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/tasks/all";
    }
}
