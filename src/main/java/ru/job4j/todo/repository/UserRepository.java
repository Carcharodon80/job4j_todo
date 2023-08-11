package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final CrudRepository crudRepository;
    private static final String FIND_USER = "FROM User WHERE login = :login AND password = :password";

    public boolean addUser(User user) {
        boolean rsl = false;
        try {
            crudRepository.run(session -> session.persist(user));
            rsl = true;
        } catch (HibernateException ignored) {
        }
        return rsl;
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        return crudRepository.optional(FIND_USER, User.class, Map.of("login", login, "password", password));
    }
}
