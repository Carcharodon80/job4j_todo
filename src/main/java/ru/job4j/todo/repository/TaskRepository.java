package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class TaskRepository {
    private final SessionFactory sf;
    private static final String FIND_ALL = "FROM Task";
    private static final String FIND_DONE = "FROM Task WHERE done = true";
    private static final String FIND_UNDONE = "FROM Task WHERE done = false";

    public List<Task> findAllTasks() {
        return findTasks(FIND_ALL);
    }

    public List<Task> findDoneTasks() {
        return findTasks(FIND_DONE);
    }

    public List<Task> findUndoneTasks() {
        return findTasks(FIND_UNDONE);
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

    public Task findTaskById(int id) {
        Session session = sf.openSession();
        Task task = new Task();
        try {
            session.beginTransaction();
            task = session.get(Task.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return task;
    }

    public boolean deleteTask(Integer id) {
        Session session = sf.openSession();
        Task task = new Task();
        task.setId(id);
        boolean result = false;
        try {
            session.beginTransaction();
            session.delete(task);
            session.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    public boolean updateTask(Task task) {
        Session session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            session.update(task);
            session.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }
}
