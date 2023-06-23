package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public boolean addUser(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public Optional<User> findUser(String login, String password) {
        return userRepository.findUserByLoginAndPassword(login, password);
    }
}
