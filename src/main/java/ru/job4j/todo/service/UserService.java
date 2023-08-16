package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;

import java.util.Optional;

@Service
public interface UserService {
    boolean addUser(User user);

    Optional<User> findUser(String login, String password);

    Optional<User> findUserById(int id);
}
