package ru.job4j.todo.repository;

import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskRepository {
    private final CrudRepository crudRepository;
    private static final String FIND_ALL = "FROM Task t JOIN FETCH t.priority ORDER BY t.id";
    private static final String FIND_SOME = "FROM Task t JOIN FETCH t.priority WHERE done = :done ORDER BY t.id";
    private static final String DELETE = "DELETE Task WHERE id = :id";
    private static final String CHANGE_STATUS = "UPDATE Task SET done = :done WHERE id = :id";
    private static final Logger LOGGER = LogManager.getLogger(TaskRepository.class.getName());

    public List<Task> findAllTasks() {
        return crudRepository.query(FIND_ALL, Task.class);
    }

    public List<Task> findDoneTasks(boolean done) {
        return crudRepository.query(FIND_SOME, Task.class, Map.of("done", done));
    }

    public void addTask(Task task) {
        crudRepository.run(session -> session.persist(task));
    }

    public Optional<Task> findTaskById(int id) {
        return crudRepository.optional(id, Task.class);
    }

    public boolean deleteTask(int id) {
        boolean rsl = false;
        try {
            crudRepository.run(DELETE, Map.of("id", id));
            rsl = true;
        } catch (Exception e) {
            LOGGER.error("___Exception___", e);
        }
        return rsl;
    }

    public boolean updateTask(Task task) {
        boolean rsl = false;
        try {
            crudRepository.run(session -> session.merge(task));
            rsl = true;
        } catch (Exception e) {
            LOGGER.error("___Exception___", e);
        }
        return rsl;
    }

    public boolean changeStatusOfTask(int id, boolean done) {
        boolean rsl = false;
        try {
            crudRepository.run(CHANGE_STATUS, Map.of("id", id, "done", !done));
            rsl = true;
        } catch (Exception e) {
            LOGGER.error("___Exception___", e);
        }
        return rsl;
    }
}
