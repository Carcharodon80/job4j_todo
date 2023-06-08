package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    public Optional<User> addUser(User user) {
        Optional<User> optional = Optional.empty();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            optional = Optional.of(user);
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return optional;
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        Optional<User> optional = Optional.empty();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            User user = session.createQuery("from User where login = :login " +
                            "and password = :password", User.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
            session.getTransaction().commit();
            optional = optional.of(user);
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return optional;
    }

    //TODO создать форму регистрации пользователя и контроллер, сервис добавления пользователя
}
