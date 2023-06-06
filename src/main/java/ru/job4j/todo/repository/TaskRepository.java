package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskRepository {
    private final SessionFactory sf;
    private static final String FIND_ALL = "FROM Task ORDER BY id";
    private static final String FIND_DONE = "FROM Task WHERE done = true ORDER BY id ";
    private static final String FIND_UNDONE = "FROM Task WHERE done = false ORDER BY id";
    private static final String DELETE  = "DELETE Task WHERE id = :id";

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

    public Optional<Task> findTaskById(int id) {
        Session session = sf.openSession();
        Optional<Task> optional = Optional.empty();
        try {
            session.beginTransaction();
            Task task = session.get(Task.class, id);
            session.getTransaction().commit();
            optional = Optional.of(task);
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return optional;
    }

    public boolean deleteTask(int id) {
        Session session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            session.createQuery(DELETE).setParameter("id", id).executeUpdate();
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

    public boolean changeStatusOfTask(int id, boolean done) {
        boolean result = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery("UPDATE Task SET done = :done WHERE id = :id")
                    .setParameter("done", !done)
                    .setParameter("id", id)
                    .executeUpdate();
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
