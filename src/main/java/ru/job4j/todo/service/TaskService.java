package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;

import java.util.List;

@Service
public interface TaskService {
    Task addTask(Task task);

    boolean deleteTask(int id);

    boolean updateTask(int id);

    List<Task> findAllTasks();

    List<Task> findDoneTasks();

    List<Task> findUndoneTasks();

    Task findTaskById(int id);

    boolean changeStatusOfTask(int id);

    boolean updateDescription(int id, String newDescription);
}
