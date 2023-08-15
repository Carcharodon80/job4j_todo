package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final CrudRepository crudRepository;
    private static final String FIND_USER = "FROM User WHERE login = :login AND password = :password";
    private static final Logger LOGGER = LogManager.getLogger(UserRepository.class);

    public boolean addUser(User user) {
        boolean rsl = false;
        try {
            crudRepository.run(session -> session.persist(user));
            rsl = true;
        } catch (Exception e) {
            LOGGER.error("___Exception___", e);
        }
        return rsl;
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        return crudRepository.optional(FIND_USER, User.class, Map.of("login", login, "password", password));
    }
}
