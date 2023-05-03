package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class TaskStore {
    private final SessionFactory sf;
    private static final String findAll = "FROM Task";
    private static final String findDone = "FROM Task WHERE done = true";
    private static final String findUndone = "FROM Task WHERE done = false";


    public List<Task> findAllTasks() {
        return findTasks(findAll);
    }

    public List<Task> findDoneTasks() {
        return findTasks(findDone);
    }

    public List<Task> findUndoneTasks() {
        return findTasks(findUndone);
    }

    private List<Task> findTasks(String query) {
        List<Task> tasks = new ArrayList<>();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            tasks = session.createQuery(query, Task.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return tasks;
    }

    public void addTask(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
