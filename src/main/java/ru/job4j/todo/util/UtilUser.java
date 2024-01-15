package ru.job4j.todo.util;

import ru.job4j.todo.model.User;

import javax.servlet.http.HttpSession;
import java.util.TimeZone;

public final class UtilUser {
    private UtilUser() {
    }

    public static User getUserFromSession(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
            user.setTimezone(TimeZone.getDefault());
        }
        return user;
    }
}
